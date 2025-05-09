package in.koreatech.koin.common.exception;

public interface BaseErrorCode {
    ErrorCausedBy causedBy();
    String getExplainError() throws NoSuchFieldError;
}
