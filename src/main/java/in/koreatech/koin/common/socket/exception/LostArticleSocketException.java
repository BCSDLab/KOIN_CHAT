package in.koreatech.koin.common.socket.exception;

import in.koreatech.koin.common.exception.BaseErrorCode;
import in.koreatech.koin.common.exception.KoinException;

public class LostArticleSocketException extends KoinException {

    public LostArticleSocketException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
