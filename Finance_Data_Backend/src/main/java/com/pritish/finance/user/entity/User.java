package com.pritish.finance.user.entity;

import jakarta.persistence.*;
import lombok.*;
import com.pritish.finance.role.entity.Role;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    private String status; // ACTIVE / INACTIVE

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}