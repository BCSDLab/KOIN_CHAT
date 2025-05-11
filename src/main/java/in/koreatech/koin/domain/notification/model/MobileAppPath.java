package in.koreatech.koin.domain.notification.model;

import lombok.Getter;

@Getter
public enum MobileAppPath {
    CHAT("chat");

    private final String path;

    MobileAppPath(String path) {
        this.path = path;
    }
}

