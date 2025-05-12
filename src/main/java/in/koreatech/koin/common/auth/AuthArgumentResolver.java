package in.koreatech.koin.common.auth;

import static in.koreatech.koin.domain.user.model.UserType.*;
import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import in.koreatech.koin.common.auth.exception.AuthorizationErrorCode;
import in.koreatech.koin.common.auth.exception.AuthorizationException;
import in.koreatech.koin.domain.user.model.User;
import in.koreatech.koin.domain.user.model.UserType;
import in.koreatech.koin.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserRepository userRepository;
    private final AuthContext authContext;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Auth.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

        Auth authAt = parameter.getParameterAnnotation(Auth.class);
        requireNonNull(authAt);
        List<UserType> permitStatus = Arrays.asList(authAt.permit());
        if (authContext.isAnonymous() && authAt.anonymous()) {
            return null;
        }
        Integer userId = authContext.getUserId();
        User user = userRepository.getById(userId);

        if (permitStatus.contains(user.getUserType())) {
            if (!user.isAuthed()) {
                if (user.getUserType() == OWNER) {
                    throw new AuthorizationException(AuthorizationErrorCode.UNAUTHORIZED_OWNER);
                }
                if (user.getUserType() == STUDENT) {
                    throw new AuthorizationException(AuthorizationErrorCode.UNAUTHORIZED_STUDENT);
                }
                if (user.getUserType() == ADMIN) {
                    throw new AuthorizationException(AuthorizationErrorCode.UNAUTHORIZED_ADMIN);
                }
                throw new AuthorizationException(AuthorizationErrorCode.UNAUTHORIZED_UNKNOWN_USER);
            }
            return user.getId();
        }
        throw new AuthorizationException(AuthorizationErrorCode.UNAUTHORIZED_INVALID_REQUEST);
    }
}
