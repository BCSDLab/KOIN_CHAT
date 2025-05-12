package in.koreatech.koin.domain.lostitem.chatroom.exception;

import in.koreatech.koin.common.exception.BaseErrorCode;
import in.koreatech.koin.common.exception.KoinException;

public class LostItemChatRoomException extends KoinException {

    public LostItemChatRoomException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
