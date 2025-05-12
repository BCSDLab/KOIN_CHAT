package in.koreatech.koin.domain.session.exception;

import in.koreatech.koin.common.exception.BaseErrorCode;
import in.koreatech.koin.common.exception.ErrorCausedBy;
import in.koreatech.koin.common.exception.HttpStatusCode;
import in.koreatech.koin.common.exception.ReasonCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserSocketSessionErrorCode implements BaseErrorCode {

    /* 400 BAD_REQUEST */
    INVALID_ACTIVE_CHAT_ROOM_STATE(
        HttpStatusCode.BAD_REQUEST,
        ReasonCode.INVALID_REQUEST,
        "ACTIVE_CHAT_ROOM 상태에서는 유효한 articleId 및 chatRoomId가 필요합니다."
    ),

    /* 500 INTERNAL_SERVER_ERROR - 세션 필드 검증 */
    NULL_USER_ID(
        HttpStatusCode.INTERNAL_SERVER_ERROR,
        ReasonCode.UNEXPECTED_ERROR,
        "웹소켓 사용자 세션 에러: userId는 null일 수 없습니다."
    ),
    NULL_DEVICE_TOKEN(
        HttpStatusCode.INTERNAL_SERVER_ERROR,
        ReasonCode.UNEXPECTED_ERROR,
        "웹소켓 사용자 세션 에러: deviceToken은 null일 수 없습니다."
    ),
    NULL_STATUS(
        HttpStatusCode.INTERNAL_SERVER_ERROR,
        ReasonCode.UNEXPECTED_ERROR,
        "웹소켓 사용자 세션 에러: status는 null일 수 없습니다."
    ),
    NULL_LAST_ACTIVE_AT(
        HttpStatusCode.INTERNAL_SERVER_ERROR,
        ReasonCode.UNEXPECTED_ERROR,
        "웹소켓 사용자 세션 에러: lastActiveAt은 null일 수 없습니다."
    ),

    /* 500 INTERNAL_SERVER_ERROR - Redis 관련 */
    REDIS_SESSION_SAVE_FAILED(
        HttpStatusCode.INTERNAL_SERVER_ERROR,
        ReasonCode.UNEXPECTED_ERROR,
        "웹소켓 사용자 세션 저장 실패"
    ),
    REDIS_SESSION_FIND_FAILED(
        HttpStatusCode.INTERNAL_SERVER_ERROR,
        ReasonCode.UNEXPECTED_ERROR,
        "웹소켓 사용자 세션 탐색 실패"
    ),
    REDIS_SESSION_TTL_FETCH_FAILED(
        HttpStatusCode.INTERNAL_SERVER_ERROR,
        ReasonCode.UNEXPECTED_ERROR,
        "웹소켓 사용자 세션 TTL 탐색 실패"
    ),
    REDIS_SESSION_EXIST_CHECK_FAILED(
        HttpStatusCode.INTERNAL_SERVER_ERROR,
        ReasonCode.UNEXPECTED_ERROR,
        "웹소켓 사용자 세션 존재 여부 확인 실패"
    ),
    REDIS_SESSION_TTL_RESET_FAILED(
        HttpStatusCode.INTERNAL_SERVER_ERROR,
        ReasonCode.UNEXPECTED_ERROR,
        "웹소켓 사용자 세션 TTL 초기화 실패"
    ),
    REDIS_SESSION_DELETE_FAILED(
        HttpStatusCode.INTERNAL_SERVER_ERROR,
        ReasonCode.UNEXPECTED_ERROR,
        "웹소켓 사용자 세션 삭제 실패"
    ),

    /* 500 INTERNAL_SERVER_ERROR - 직렬화/역직렬화 */
    SESSION_SERIALIZATION_FAILED(
        HttpStatusCode.INTERNAL_SERVER_ERROR,
        ReasonCode.UNEXPECTED_ERROR,
        "웹소켓 사용자 세션 직렬화 실패"
    ),
    SESSION_DESERIALIZATION_FAILED(
        HttpStatusCode.INTERNAL_SERVER_ERROR,
        ReasonCode.UNEXPECTED_ERROR,
        "웹소켓 사용자 세션 역직렬화 실패"
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
