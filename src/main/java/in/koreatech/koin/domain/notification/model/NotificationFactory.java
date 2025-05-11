package in.koreatech.koin.domain.notification.model;

import org.springframework.stereotype.Component;

import in.koreatech.koin.domain.user.model.User;

@Component
public class NotificationFactory {

    public Notification generateChatMessageNotification(
        MobileAppPath path,
        Integer articleId,
        Integer chatRoomId,
        String senderName,
        String messageContent,
        User target
    ) {
        return new Notification(
            path,
            generateChatMessageSchemeUri(path, articleId, chatRoomId),
            String.format("%s님의 메시지", senderName),
            messageContent,
            null,
            NotificationType.MESSAGE,
            target
        );
    }

    private String generateSchemeUri(MobileAppPath path, Integer eventId) {
        if (eventId == null) {
            return path.getPath();
        }
        return String.format("%s?id=%d", path.getPath(), eventId);
    }

    private String getPostposition(String place, String firstPost, String secondPost) {
        char lastChar = place.charAt(place.length() - 1);
        String result = (lastChar - 0xAC00) % 28 > 0 ? firstPost : secondPost;
        return place + result;
    }


    private String generateChatMessageSchemeUri(MobileAppPath path, Integer articleId, Integer chatRoomId) {
        if (chatRoomId == null) {
            return generateSchemeUri(path, articleId);
        }
        return String.format("%s?articleId=%d&chatRoomId=%d", path.getPath(), articleId, chatRoomId);
    }
}
