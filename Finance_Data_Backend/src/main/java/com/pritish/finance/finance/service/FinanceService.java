package com.pritish.finance.finance.service;

import com.pritish.finance.finance.dto.*;
import java.util.List;

public interface FinanceService {

    FinanceResponse create(FinanceRequest request, String userEmail);

    List<FinanceResponse> getAll();

    FinanceResponse update(Long id, FinanceRequest request);

    void delete(Long id);

    List<FinanceResponse> filterByType(String type);

    List<FinanceResponse> filterByCategory(String category);

    List<FinanceResponse> filterByDateRange(String start, String end);
}