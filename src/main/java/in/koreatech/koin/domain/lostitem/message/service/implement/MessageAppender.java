package in.koreatech.koin.domain.lostitem.message.service.implement;

import org.springframework.stereotype.Component;

import in.koreatech.koin.domain.lostitem.message.model.ChatMessageEntity;
import in.koreatech.koin.domain.lostitem.message.repository.ChatMessageRedisRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessageAppender {

    private final ChatMessageRedisRepository chatMessageRedisRepository;

    public void save(ChatMessageEntity message) {
        chatMessageRedisRepository.save(message);
    }
}
