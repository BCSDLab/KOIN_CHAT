package in.koreatech.koin.common.exception;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(KoinException.class)
    protected ResponseEntity<Object> handleGlobalErrorException(HttpServletRequest request, KoinException e) {
        log.warn("handleGlobalErrorException : {}", e.getMessage());
        requestLogging(request);
        return buildErrorResponse(e.causedBy().getStatusCode(), e.getMessage());
    }

    private ResponseEntity<Object> buildErrorResponse(
        HttpStatusCode httpStatusCode,
        String message
    ) {
        String errorTraceId = UUID.randomUUID().toString();
        log.warn("traceId: {}", errorTraceId);
        var response = new ErrorResponse(httpStatusCode.getCode(), message, errorTraceId);
        return ResponseEntity.status(httpStatusCode.getCode()).body(response);
    }

    private void requestLogging(HttpServletRequest request) {
        log.info("request header: {}", getHeaders(request));
        log.info("request query string: {}", getQueryString(request));
        log.info("request body: {}", getRequestBody(request));
    }

    private Map<String, Object> getHeaders(HttpServletRequest request) {
        Map<String, Object> headerMap = new HashMap<>();
        Enumeration<String> headerArray = request.getHeaderNames();
        while (headerArray.hasMoreElements()) {
            String headerName = headerArray.nextElement();
            headerMap.put(headerName, request.getHeader(headerName));
        }
        return headerMap;
    }

    private String getQueryString(HttpServletRequest httpRequest) {
        String queryString = httpRequest.getQueryString();
        if (queryString == null) {
            return " - ";
        }
        return queryString;
    }

    private String getRequestBody(HttpServletRequest request) {
        var wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper == null) {
            return " - ";
        }
        try {
            // body 가 읽히지 않고 예외처리 되는 경우에 캐시하기 위함
            wrapper.getInputStream().readAllBytes();
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length == 0) {
                return " - ";
            }
            return new String(buf, wrapper.getCharacterEncoding());
        } catch (Exception e
        ) {
            return " - ";
        }
    }
}
