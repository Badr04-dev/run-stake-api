package fr.strivestake.user.service;

import fr.strivestake.user.criteria.UserCriteria;
import fr.strivestake.user.model.User;
import fr.strivestake.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static fr.strivestake.user.model.AccountStatusEnum.BANNED;

@Service
@RequiredArgsConstructor
public class SearchUserService {

    private final UserRepository userRepository;

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public boolean isUserBanned(String email) {
        return userRepository.exists(
                new UserCriteria()
                        .email(email)
                        .accountStatus(BANNED)
        );
    }

}
