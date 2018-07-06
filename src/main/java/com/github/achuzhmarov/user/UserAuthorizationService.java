package com.github.achuzhmarov.user;

import com.github.achuzhmarov.common.exception.DataNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAuthorizationService {
    private final UserRepository userRepository;

    private String currentUserLogin;

    public UserAuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public AppUser getCurrentUser() {
        return userRepository.findByLogin(currentUserLogin)
                .orElseThrow(() -> new DataNotFoundException("User", currentUserLogin));
    }

    public void authorizeUser(String login) {
        currentUserLogin = login;
    }
}
