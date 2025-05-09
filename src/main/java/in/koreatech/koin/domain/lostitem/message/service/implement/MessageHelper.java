package in.koreatech.koin.domain.lostitem.message.service.implement;

import org.springframework.stereotype.Component;

import in.koreatech.koin.domain.lostitem.message.model.ChatMessageEntity;
import in.koreatech.koin.domain.lostitem.message.util.ChatMessageIdGenerator;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessageHelper {

    private final ChatMessageIdGenerator tsidGenerator;

    public void generateMessageId(ChatMessageEntity message) {
        message.addMessageId(tsidGenerator.generate());
    }
}
