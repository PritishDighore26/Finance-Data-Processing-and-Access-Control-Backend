package com.pritish.finance.role.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.pritish.finance.role.entity.Role;
import com.pritish.finance.role.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }
}