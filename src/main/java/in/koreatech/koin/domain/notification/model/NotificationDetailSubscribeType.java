package in.koreatech.koin.domain.notification.model;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

import in.koreatech.koin.domain.notification.exception.NotificationErrorCode;
import in.koreatech.koin.domain.notification.exception.NotificationException;
import lombok.Getter;

@Getter
public enum NotificationDetailSubscribeType {
    BREAKFAST,
    LUNCH,
    DINNER,
    ;

    @JsonCreator
    public static NotificationDetailSubscribeType from(String detailType) {
        return Arrays.stream(values())
            .filter(it -> it.name().equalsIgnoreCase(detailType))
            .findAny()
            .orElseThrow(() -> new NotificationException(NotificationErrorCode.SUBSCRIBE_NOT_FOUND));
    }

    // public static NotificationDetailSubscribeType from(DiningType diningType) {
    //     return NotificationDetailSubscribeType.valueOf(diningType.name());
    // }
}
