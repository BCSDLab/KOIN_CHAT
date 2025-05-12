package in.koreatech.koin.domain.lostitem.chatroom.exception;

import in.koreatech.koin.common.exception.BaseErrorCode;
import in.koreatech.koin.common.exception.ErrorCausedBy;
import in.koreatech.koin.common.exception.HttpStatusCode;
import in.koreatech.koin.common.exception.ReasonCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LostItemChatRoomErrorCode implements BaseErrorCode {

    /* 403 FORBIDDEN */
    BLOCKED_USER(HttpStatusCode.FORBIDDEN, ReasonCode.ACCESS_TO_THE_REQUESTED_RESOURCE_IS_FORBIDDEN, "차단된 사용자입니다."),

    /* 500 INTERNAL_SERVER_ERROR */
    SELF_CHAT_NOT_ALLOWED(HttpStatusCode.INTERNAL_SERVER_ERROR, ReasonCode.UNEXPECTED_ERROR,
        "사용자가 자신과 채팅방을 생성할 수 없습니다.");

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
