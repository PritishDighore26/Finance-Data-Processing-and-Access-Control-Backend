package com.pritish.finance.user.service;

import com.pritish.finance.user.entity.User;

public interface UserService {
    User save(User user);
    User findByEmail(String email);
}