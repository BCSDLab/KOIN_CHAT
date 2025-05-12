package in.koreatech.koin.common.socket.interceptor;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import in.koreatech.koin.common.auth.JwtProvider;
import in.koreatech.koin.common.auth.exception.AuthenticationErrorCode;
import in.koreatech.koin.common.auth.exception.AuthenticationException;
import in.koreatech.koin.common.socket.model.UserPrincipal;
import in.koreatech.koin.domain.session.model.UserSession;
import in.koreatech.koin.domain.session.model.UserSessionStatus;
import in.koreatech.koin.domain.session.service.UserSessionService;
import in.koreatech.koin.domain.user.model.User;
import in.koreatech.koin.domain.user.service.UserReader;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ConnectInterceptor implements ChannelInterceptor {

    private final UserReader userReader;
    private final UserSessionService userSessionService;
    private final JwtProvider jwtProvider;

    @Override
    public Message<?> preSend(@NonNull Message<?> message, @NonNull MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())) {
            // JWT 토큰 추출 및 검증
            String accessToken = accessor.getFirstNativeHeader("Authorization");
            if (accessToken == null) {
                throw new AuthenticationException(AuthenticationErrorCode.MISSING_JWT_TOKEN);
            }

            Integer userId = jwtProvider.getUserId(accessToken);

            User user = userReader.readUser(userId);
            UserPrincipal principal = UserPrincipal.of(user);

            // WebSocket 세션에 User Principal 설정
            accessor.setUser(principal);

            // 사용자 세션 활성화
            if (userSessionService.exists(principal.getUserId())) {
                userSessionService.updateUserStatus(principal.getUserId(), UserSessionStatus.ACTIVE_APP);
            } else {
                userSessionService.save(
                    principal.getUserId(),
                    UserSession.of(principal.getUserId(), principal.getDeviceToken())
                );
            }
        }

        return message;
    }
}
