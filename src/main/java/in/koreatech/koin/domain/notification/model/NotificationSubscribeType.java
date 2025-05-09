package in.koreatech.koin.domain.notification.model;

import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum NotificationSubscribeType {
    LOST_ITEM_CHAT,
    ;

    @JsonCreator
    public static NotificationSubscribeType from(String type) {
        return Arrays.stream(values())
            .filter(it -> it.name().equalsIgnoreCase(type))
            .findAny()
            .orElseThrow(() -> SubscribeNotFoundException.withDetail("type: " + type));
    }
}
