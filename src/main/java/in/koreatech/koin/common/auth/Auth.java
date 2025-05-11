package in.koreatech.koin.common.auth;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import in.koreatech.koin.domain.user.model.UserType;
import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface Auth {

    UserType[] permit() default {};

    boolean anonymous() default false;
}
