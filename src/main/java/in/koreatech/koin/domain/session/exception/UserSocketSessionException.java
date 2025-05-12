package in.koreatech.koin.domain.session.exception;

import in.koreatech.koin.common.exception.BaseErrorCode;
import in.koreatech.koin.common.exception.KoinException;

public class UserSocketSessionException extends KoinException {

    public UserSocketSessionException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
