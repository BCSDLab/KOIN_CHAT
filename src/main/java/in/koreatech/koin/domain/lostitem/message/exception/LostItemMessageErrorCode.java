package in.koreatech.koin.domain.lostitem.message.exception;

import in.koreatech.koin.common.exception.BaseErrorCode;
import in.koreatech.koin.common.exception.ErrorCausedBy;
import in.koreatech.koin.common.exception.HttpStatusCode;
import in.koreatech.koin.common.exception.ReasonCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LostItemMessageErrorCode implements BaseErrorCode {

    /* 500 INTERNAL_SERVER_ERROR */
    MESSAGE_SAVE_FAILED(HttpStatusCode.INTERNAL_SERVER_ERROR, ReasonCode.UNEXPECTED_ERROR, "메시지 저장 실패"),
    MESSAGE_UPDATE_FAILED(HttpStatusCode.INTERNAL_SERVER_ERROR, ReasonCode.UNEXPECTED_ERROR, "메시지 업데이트 실패");

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
