package in.koreatech.koin.common.auth.exception;

import in.koreatech.koin.common.exception.BaseErrorCode;
import in.koreatech.koin.common.exception.ErrorCausedBy;
import in.koreatech.koin.common.exception.HttpStatusCode;
import in.koreatech.koin.common.exception.ReasonCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AuthorizationErrorCode implements BaseErrorCode {

    /* 401 UNAUTHORIZED */
    UNAUTHORIZED_OWNER(
        HttpStatusCode.UNAUTHORIZED,
        ReasonCode.MISSING_OR_INVALID_AUTHENTICATION_CREDENTIALS,
        "관리자 인증 대기중입니다."
    ),
    UNAUTHORIZED_STUDENT(
        HttpStatusCode.UNAUTHORIZED,
        ReasonCode.MISSING_OR_INVALID_AUTHENTICATION_CREDENTIALS,
        "미인증 상태입니다. 아우누리에서 인증메일을 확인해주세요"
    ),
    UNAUTHORIZED_ADMIN(
        HttpStatusCode.UNAUTHORIZED,
        ReasonCode.MISSING_OR_INVALID_AUTHENTICATION_CREDENTIALS,
        "PL 인증 대기중입니다."
    ),
    UNAUTHORIZED_UNKNOWN_USER(
        HttpStatusCode.UNAUTHORIZED,
        ReasonCode.MISSING_OR_INVALID_AUTHENTICATION_CREDENTIALS,
        "인증되지 않은 사용자입니다."
    ),
    UNAUTHORIZED_INVALID_REQUEST(
        HttpStatusCode.UNAUTHORIZED,
        ReasonCode.MISSING_OR_INVALID_AUTHENTICATION_CREDENTIALS,
        "잘못된 인증 요청입니다."
    );

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
