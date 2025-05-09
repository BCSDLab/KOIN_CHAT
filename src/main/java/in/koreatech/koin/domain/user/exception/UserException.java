package in.koreatech.koin.domain.user.exception;

import in.koreatech.koin.common.exception.BaseErrorCode;
import in.koreatech.koin.common.exception.KoinException;

public class UserException extends KoinException {

    public UserException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
