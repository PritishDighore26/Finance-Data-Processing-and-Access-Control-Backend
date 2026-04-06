package com.pritish.finance.finance.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

import com.pritish.finance.user.entity.User;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "finance_records")
public class FinanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String type; // INCOME / EXPENSE

    private String category;

    private LocalDate date;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}