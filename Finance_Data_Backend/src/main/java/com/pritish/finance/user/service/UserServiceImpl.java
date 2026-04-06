package com.pritish.finance.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.pritish.finance.user.entity.User;
import com.pritish.finance.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}