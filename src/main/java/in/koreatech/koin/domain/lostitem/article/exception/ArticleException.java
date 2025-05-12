package in.koreatech.koin.domain.lostitem.article.exception;

import in.koreatech.koin.common.exception.BaseErrorCode;
import in.koreatech.koin.common.exception.KoinException;

public class ArticleException extends KoinException {

    public ArticleException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
