package in.koreatech.koin.domain.notification.exception;

import in.koreatech.koin.common.exception.BaseErrorCode;
import in.koreatech.koin.common.exception.KoinException;

public class NotificationException extends KoinException {

    public NotificationException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
