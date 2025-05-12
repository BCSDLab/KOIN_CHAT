package in.koreatech.koin.common.auth;

import java.util.Objects;



import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import in.koreatech.koin.common.auth.exception.AuthenticationErrorCode;
import in.koreatech.koin.common.auth.exception.AuthenticationException;
import in.koreatech.koin.domain.user.model.UserType;

@Component
@RequestScope
public class AuthContext {

    private Integer userId;

    public Integer getUserId() {
        if (userId == null) {
            throw new AuthenticationException(AuthenticationErrorCode.USER_ID_NULL);
        }
        return userId;
    }

    public boolean isAnonymous() {
        return Objects.equals(userId, UserType.ANONYMOUS_ID);
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
