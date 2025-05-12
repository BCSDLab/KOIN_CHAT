package in.koreatech.koin.common.exception;

public class KoinException extends RuntimeException{

    private final BaseErrorCode baseErrorCode;

    public KoinException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode.causedBy().reasonCode().name());
        this.baseErrorCode = baseErrorCode;
    }

    public ErrorCausedBy causedBy() {
        return baseErrorCode.causedBy();
    }

    @Override
    public String toString() {
        return "GlobalErrorException(code=" + baseErrorCode.causedBy().getCode()
            + ", message=" + baseErrorCode.getExplainError() + ")";
    }
}
