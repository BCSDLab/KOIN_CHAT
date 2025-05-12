package in.koreatech.koin.common.auth.exception;

import in.koreatech.koin.common.exception.BaseErrorCode;
import in.koreatech.koin.common.exception.ErrorCausedBy;
import in.koreatech.koin.common.exception.HttpStatusCode;
import in.koreatech.koin.common.exception.ReasonCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AuthenticationErrorCode implements BaseErrorCode {

    /* 401 UNAUTHORIZED */
    MISSING_JWT_TOKEN(HttpStatusCode.UNAUTHORIZED, ReasonCode.MISSING_OR_INVALID_AUTHENTICATION_CREDENTIALS,
        "토큰 정보가 유효하지 않습니다."),

    USER_ID_NULL(HttpStatusCode.UNAUTHORIZED, ReasonCode.MISSING_OR_INVALID_AUTHENTICATION_CREDENTIALS,
        "userId is null"),

    /* 401 FORBIDDEN */
    INVALID_JWT_TOKEN(HttpStatusCode.FORBIDDEN, ReasonCode.MISSING_OR_INVALID_AUTHENTICATION_CREDENTIALS,
        "Authorization 헤더가 없거나 Bearer 토큰이 아닙니다.");

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
