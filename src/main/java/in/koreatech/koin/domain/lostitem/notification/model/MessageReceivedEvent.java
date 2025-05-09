package in.koreatech.koin.domain.lostitem.notification.model;

import in.koreatech.koin.domain.lostitem.message.model.ChatMessageCommand;

public record MessageReceivedEvent(
    Integer articleId,
    Integer chatRoomId,
    Integer userId,
    ChatMessageCommand messageCommand
) {
}
