package com.pritish.finance.dashboard.service;

import com.pritish.finance.dashboard.dto.*;
import com.pritish.finance.finance.dto.FinanceResponse;

import java.util.List;

public interface DashboardService {

    SummaryResponse getSummary();

    List<CategorySummary> getCategorySummary();

    List<TrendResponse> getMonthlyTrends();

    List<FinanceResponse> getRecentTransactions();
}