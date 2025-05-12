package in.koreatech.koin.domain.user.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import in.koreatech.koin.domain.user.exception.UserErrorCode;
import in.koreatech.koin.domain.user.exception.UserException;
import in.koreatech.koin.domain.user.model.User;

public interface UserRepository extends Repository<User, Integer> {

    Optional<User> findById(Integer id);

    default User getById(Integer userId) {
        return findById(userId)
            .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
    }
}
