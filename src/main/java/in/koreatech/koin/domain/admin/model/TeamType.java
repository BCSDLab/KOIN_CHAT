package in.koreatech.koin.domain.admin.model;

import lombok.Getter;

@Getter
public enum TeamType {
    KOIN("Koin"),
    BUSINESS("Business"),
    CAMPUS("Campus"),
    USER("User"),
    ;

    private final String value;

    TeamType(String value) {
        this.value = value;
    }
}
