package in.koreatech.koin.domain.lostitem.message.exception;

import in.koreatech.koin.common.exception.BaseErrorCode;
import in.koreatech.koin.common.exception.KoinException;

public class LostItemMessageException extends KoinException {

    public LostItemMessageException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
