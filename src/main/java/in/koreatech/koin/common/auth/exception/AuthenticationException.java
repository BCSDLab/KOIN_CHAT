package in.koreatech.koin.common.auth.exception;

import in.koreatech.koin.common.exception.BaseErrorCode;
import in.koreatech.koin.common.exception.KoinException;

public class AuthenticationException extends KoinException {

    public AuthenticationException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
