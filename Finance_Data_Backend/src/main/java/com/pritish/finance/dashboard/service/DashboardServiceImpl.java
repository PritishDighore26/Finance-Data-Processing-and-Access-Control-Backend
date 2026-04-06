package com.pritish.finance.dashboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.pritish.finance.dashboard.dto.*;
import com.pritish.finance.finance.entity.FinanceRecord;
import com.pritish.finance.finance.repository.FinanceRepository;
import com.pritish.finance.finance.dto.FinanceResponse;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final FinanceRepository financeRepository;

    @Override
    public SummaryResponse getSummary() {

        List<FinanceRecord> records = financeRepository.findAll();

        double income = records.stream()
                .filter(r -> r.getType().equalsIgnoreCase("INCOME"))
                .mapToDouble(FinanceRecord::getAmount)
                .sum();

        double expense = records.stream()
                .filter(r -> r.getType().equalsIgnoreCase("EXPENSE"))
                .mapToDouble(FinanceRecord::getAmount)
                .sum();

        return new SummaryResponse(income, expense, income - expense);
    }

    @Override
    public List<CategorySummary> getCategorySummary() {

        List<FinanceRecord> records = financeRepository.findAll();

        Map<String, Double> map = records.stream()
                .collect(Collectors.groupingBy(
                        FinanceRecord::getCategory,
                        Collectors.summingDouble(FinanceRecord::getAmount)
                ));

        return map.entrySet().stream()
                .map(e -> new CategorySummary(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TrendResponse> getMonthlyTrends() {

        List<FinanceRecord> records = financeRepository.findAll();

        Map<String, List<FinanceRecord>> grouped = records.stream()
                .collect(Collectors.groupingBy(r -> r.getDate().getMonth().toString()));

        List<TrendResponse> trends = new ArrayList<>();

        for (String month : grouped.keySet()) {

            double income = grouped.get(month).stream()
                    .filter(r -> r.getType().equalsIgnoreCase("INCOME"))
                    .mapToDouble(FinanceRecord::getAmount)
                    .sum();

            double expense = grouped.get(month).stream()
                    .filter(r -> r.getType().equalsIgnoreCase("EXPENSE"))
                    .mapToDouble(FinanceRecord::getAmount)
                    .sum();

            trends.add(new TrendResponse(month, income, expense));
        }

        return trends;
    }

    @Override
    public List<FinanceResponse> getRecentTransactions() {

        return financeRepository.findAll()
                .stream()
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .limit(5)
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private FinanceResponse mapToResponse(FinanceRecord record) {
        FinanceResponse res = new FinanceResponse();
        res.setId(record.getId());
        res.setAmount(record.getAmount());
        res.setType(record.getType());
        res.setCategory(record.getCategory());
        res.setDate(record.getDate());
        res.setDescription(record.getDescription());
        return res;
    }
}