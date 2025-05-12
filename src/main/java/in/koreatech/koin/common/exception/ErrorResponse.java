package in.koreatech.koin.common.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record ErrorResponse(
    @JsonIgnore int status,
    String code,
    String message,
    String errorTraceId
) {
    public ErrorResponse(int status, String message, String errorTraceId) {
        this(status, "", message, errorTraceId);
    }
}
