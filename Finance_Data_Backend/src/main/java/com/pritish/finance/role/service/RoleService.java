package com.pritish.finance.role.service;

import com.pritish.finance.role.entity.Role;

public interface RoleService {
    Role getByName(String name);
}