package in.koreatech.koin.common.socket.exception;

import in.koreatech.koin.common.exception.BaseErrorCode;
import in.koreatech.koin.common.exception.ErrorCausedBy;
import in.koreatech.koin.common.exception.HttpStatusCode;
import in.koreatech.koin.common.exception.ReasonCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LostArticleSocketErrorCode implements BaseErrorCode {

    /* 400 BAD_REQUEST */
    INVALID_SUBSCRIBE_DESTINATION(HttpStatusCode.BAD_REQUEST, ReasonCode.INVALID_REQUEST_SYNTAX, "구독 경로가 올바르지 않습니다.");

    private final HttpStatusCode statusCode;
    private final ReasonCode reasonCode;
    private final String message;

    @Override
    public ErrorCausedBy causedBy() {
        return ErrorCausedBy.of(statusCode, reasonCode);
    }

    @Override
    public String getExplainError() throws NoSuchFieldError {
        return message;
    }
}
