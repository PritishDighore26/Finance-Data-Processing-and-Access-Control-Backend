package com.pritish.finance.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pritish.finance.auth.dto.RegisterRequest;
import com.pritish.finance.role.entity.Role;
import com.pritish.finance.role.service.RoleService;
import com.pritish.finance.user.entity.User;
import com.pritish.finance.user.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String register(RegisterRequest request) {

        Role role = roleService.getByName(request.getRole());

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus("ACTIVE");
        user.setRole(role);

        userService.save(user);

        return "User Registered Successfully";
    }
}