package in.koreatech.koin.domain.user.service;

import org.springframework.stereotype.Service;

import in.koreatech.koin.domain.user.model.User;
import in.koreatech.koin.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserReader {

    private final UserRepository userRepository;

    public User readUser(Integer id) {
        return userRepository.getById(id);
    }
}
