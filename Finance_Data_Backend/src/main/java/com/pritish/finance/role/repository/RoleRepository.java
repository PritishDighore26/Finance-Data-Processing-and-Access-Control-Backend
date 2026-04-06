package com.pritish.finance.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pritish.finance.role.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}