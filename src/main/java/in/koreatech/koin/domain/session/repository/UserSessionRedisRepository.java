package in.koreatech.koin.domain.session.repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.koreatech.koin.domain.session.exception.UserSocketSessionErrorCode;
import in.koreatech.koin.domain.session.exception.UserSocketSessionException;
import in.koreatech.koin.domain.session.model.UserSession;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserSessionRedisRepository {

    private static final long TTL_SECONDS = 60 * 60 * 24 * 7; // 1주일

    private final ObjectMapper objectMapper;

    private final RedisTemplate<String, Object> redisTemplate;

    public void save(Integer userId, UserSession value) {
        try {
            String key = createKey(userId);
            String serializedValue = serialize(value);

            redisTemplate.opsForValue().set(key, serializedValue, TTL_SECONDS, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new UserSocketSessionException(UserSocketSessionErrorCode.REDIS_SESSION_SAVE_FAILED);
        }
    }

    public Optional<UserSession> findUserSession(Integer userId) {
        try {
            String key = createKey(userId);
            Object value = redisTemplate.opsForValue().get(key);
            return Optional.ofNullable(deserialize(value));
        } catch (Exception e) {
            throw new UserSocketSessionException(UserSocketSessionErrorCode.REDIS_SESSION_FIND_FAILED);
        }
    }

    public Long getSessionTtl(Integer userId) {
        try {
            String key = createKey(userId);
            Long ttl = redisTemplate.getExpire(key);
            return ttl != null ? ttl : -2L;
        } catch (Exception e) {
            throw new UserSocketSessionException(UserSocketSessionErrorCode.REDIS_SESSION_TTL_FETCH_FAILED);
        }
    }

    public boolean exists(Integer userId) {
        try {
            String key = createKey(userId);
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        } catch (Exception e) {
            throw new UserSocketSessionException(UserSocketSessionErrorCode.REDIS_SESSION_EXIST_CHECK_FAILED);
        }
    }

    public void resetSessionTtl(Integer userId) {
        try {
            String key = createKey(userId);
            redisTemplate.expire(key, TTL_SECONDS, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new UserSocketSessionException(UserSocketSessionErrorCode.REDIS_SESSION_TTL_RESET_FAILED);
        }
    }

    public void delete(Integer userId) {
        try {
            String key = createKey(userId);
            redisTemplate.delete(key);
        } catch (Exception e) {
            throw new UserSocketSessionException(UserSocketSessionErrorCode.REDIS_SESSION_DELETE_FAILED);
        }
    }

    private String createKey(Integer userId) {
        return "SocketSessionUserId:" + userId;
    }

    private String serialize(UserSession value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new UserSocketSessionException(UserSocketSessionErrorCode.SESSION_SERIALIZATION_FAILED);
        }
    }

    private UserSession deserialize(Object value) {
        if (value == null) return null;
        try {
            return objectMapper.readValue((String) value, UserSession.class);
        } catch (JsonProcessingException e) {
            throw new UserSocketSessionException(UserSocketSessionErrorCode.SESSION_DESERIALIZATION_FAILED);
        }
    }
}
