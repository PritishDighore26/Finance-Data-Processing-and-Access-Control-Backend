package com.pritish.finance.finance.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class FinanceRequest {

    private Double amount;
    private String type;
    private String category;
    private LocalDate date;
    private String description;
}