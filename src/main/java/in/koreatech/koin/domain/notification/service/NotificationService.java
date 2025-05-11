package in.koreatech.koin.domain.notification.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.koreatech.koin.domain.notification.model.Notification;
import in.koreatech.koin.domain.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final FcmClient fcmClient;

    public void push(List<Notification> notifications) {
        for (Notification notification : notifications) {
            push(notification);
        }
    }

    public void push(Notification notification) {
        notificationRepository.save(notification);
        String deviceToken = notification.getUser().getDeviceToken();
        fcmClient.sendMessage(
            deviceToken,
            notification.getTitle(),
            notification.getMessage(),
            notification.getImageUrl(),
            notification.getMobileAppPath(),
            notification.getSchemeUri(),
            notification.getType().toLowerCase()
        );
    }
}
