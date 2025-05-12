package in.koreatech.koin.common.auth.exception;

import in.koreatech.koin.common.exception.BaseErrorCode;
import in.koreatech.koin.common.exception.KoinException;

public class AuthorizationException extends KoinException {

    public AuthorizationException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
