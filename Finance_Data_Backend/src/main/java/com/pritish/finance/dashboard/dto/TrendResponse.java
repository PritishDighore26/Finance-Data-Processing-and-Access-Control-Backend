package com.pritish.finance.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrendResponse {

    private String month;
    private Double income;
    private Double expense;
}