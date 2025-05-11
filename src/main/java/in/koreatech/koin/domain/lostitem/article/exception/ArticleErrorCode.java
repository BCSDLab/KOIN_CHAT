package in.koreatech.koin.domain.lostitem.article.exception;

import in.koreatech.koin.common.exception.BaseErrorCode;
import in.koreatech.koin.common.exception.ErrorCausedBy;
import in.koreatech.koin.common.exception.HttpStatusCode;
import in.koreatech.koin.common.exception.ReasonCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ArticleErrorCode implements BaseErrorCode {

    /* 404 NOT_FOUND */
    ARTICLE_NOT_FOUND(HttpStatusCode.NOT_FOUND, ReasonCode.REQUESTED_RESOURCE_NOT_FOUND,
        "게시글이 존재하지 않습니다.");

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
