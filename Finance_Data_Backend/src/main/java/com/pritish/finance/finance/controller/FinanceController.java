package com.pritish.finance.finance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.pritish.finance.finance.dto.*;
import com.pritish.finance.finance.service.FinanceService;

import java.util.List;

@RestController
@RequestMapping("/finance")
@RequiredArgsConstructor
public class FinanceController {

    private final FinanceService financeService;

    @PostMapping
    public FinanceResponse create(@RequestBody FinanceRequest request, Authentication auth) {
        return financeService.create(request, auth.getName());
    }

    @GetMapping
    public List<FinanceResponse> getAll() {
        return financeService.getAll();
    }

    @PutMapping("/{id}")
    public FinanceResponse update(@PathVariable Long id, @RequestBody FinanceRequest request) {
        return financeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        financeService.delete(id);
        return "Deleted Successfully";
    }

    @GetMapping("/type/{type}")
    public List<FinanceResponse> filterByType(@PathVariable String type) {
        return financeService.filterByType(type);
    }

    @GetMapping("/category/{category}")
    public List<FinanceResponse> filterByCategory(@PathVariable String category) {
        return financeService.filterByCategory(category);
    }

    @GetMapping("/date")
    public List<FinanceResponse> filterByDate(
            @RequestParam String start,
            @RequestParam String end) {

        return financeService.filterByDateRange(start, end);
    }
}