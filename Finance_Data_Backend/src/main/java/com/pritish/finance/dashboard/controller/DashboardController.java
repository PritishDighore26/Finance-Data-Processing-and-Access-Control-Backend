package com.pritish.finance.dashboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.pritish.finance.dashboard.dto.*;
import com.pritish.finance.dashboard.service.DashboardService;
import com.pritish.finance.finance.dto.FinanceResponse;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/summary")
    public SummaryResponse getSummary() {
        return dashboardService.getSummary();
    }

    @GetMapping("/category")
    public List<CategorySummary> getCategorySummary() {
        return dashboardService.getCategorySummary();
    }

    @GetMapping("/trends")
    public List<TrendResponse> getTrends() {
        return dashboardService.getMonthlyTrends();
    }

    @GetMapping("/recent")
    public List<FinanceResponse> getRecent() {
        return dashboardService.getRecentTransactions();
    }
}