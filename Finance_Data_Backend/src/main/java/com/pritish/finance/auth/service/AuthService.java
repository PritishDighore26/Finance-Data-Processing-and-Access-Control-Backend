package com.pritish.finance.auth.service;

import com.pritish.finance.auth.dto.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest request);
}