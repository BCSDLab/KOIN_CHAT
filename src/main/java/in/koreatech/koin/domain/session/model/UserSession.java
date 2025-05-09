package in.koreatech.koin.domain.session.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import in.koreatech.koin.domain.session.exception.UserSocketSessionErrorCode;
import in.koreatech.koin.domain.session.exception.UserSocketSessionException;
import lombok.Getter;

@Getter
public class UserSession implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String deviceToken;

    private UserSessionStatus status;

    private Long currentArticleId;

    private Long currentChatRoomId;

    private LocalDateTime lastActiveAt;

    @JsonCreator
    private UserSession(
        @JsonProperty("userId") Integer userId,
        @JsonProperty("deviceId") String deviceToken,
        @JsonProperty("status") UserSessionStatus status,
        @JsonProperty("currentArticleId") Long currentArticleId,
        @JsonProperty("currentChatRoomId") Long currentChatRoomId,
        @JsonProperty("lastActiveAt") LocalDateTime lastActiveAt
    ) {
        validate(userId, deviceToken, status, lastActiveAt);

        this.userId = userId;
        this.deviceToken = deviceToken;
        this.status = status;
        this.currentArticleId = currentArticleId;
        this.currentChatRoomId = currentChatRoomId;
        this.lastActiveAt = lastActiveAt;
    }

    public static UserSession of(Integer userId, String deviceToken) {
        return new UserSession(userId, deviceToken, UserSessionStatus.ACTIVE_APP, -1L, -1L, LocalDateTime.now());
    }

    public void updateStatus(UserSessionStatus status, Long currentArticleId, Long currentChatRoomId) {
        validate(userId, deviceToken, status, lastActiveAt);

        if (status.equals(UserSessionStatus.ACTIVE_CHAT_ROOM) && (currentArticleId == null || currentArticleId <= 0)
            && (currentChatRoomId == null || currentChatRoomId <= 0)) {
            throw new UserSocketSessionException(UserSocketSessionErrorCode.INVALID_ACTIVE_CHAT_ROOM_STATE);
        }

        this.status = status;
        this.currentArticleId = currentArticleId;
        this.currentChatRoomId = currentChatRoomId;
        updateLastActiveAt();
    }

    public void updateLastActiveAt() {
        this.lastActiveAt = LocalDateTime.now();
    }

    @Serial
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();

        // 가변 요소를 방어적으로 복사
        this.userId = Integer.valueOf(userId);
        this.deviceToken = String.copyValueOf(deviceToken.toCharArray());
        this.status = UserSessionStatus.valueOf(status.name());
        this.lastActiveAt = LocalDateTime.of(lastActiveAt.toLocalDate(), lastActiveAt.toLocalTime());
        this.currentArticleId = this.currentArticleId == null ? -1L : currentArticleId;
        this.currentChatRoomId = this.currentChatRoomId == null ? -1L : currentChatRoomId;

        // 불변식을 만족하는지 검사
        validate(userId, deviceToken, status, lastActiveAt);
    }

    private void validate(Integer userId, String deviceToken, UserSessionStatus status, LocalDateTime lastActiveAt) {
        if (userId == null) {
            throw new UserSocketSessionException(UserSocketSessionErrorCode.NULL_USER_ID);
        }
        if (deviceToken == null) {
            throw new UserSocketSessionException(UserSocketSessionErrorCode.NULL_DEVICE_TOKEN);
        }
        if (status == null) {
            throw new UserSocketSessionException(UserSocketSessionErrorCode.NULL_STATUS);
        }
        if (lastActiveAt == null) {
            throw new UserSocketSessionException(UserSocketSessionErrorCode.NULL_LAST_ACTIVE_AT);
        }
    }
}
