package com.pritish.finance.finance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.pritish.finance.finance.dto.*;
import com.pritish.finance.finance.entity.FinanceRecord;
import com.pritish.finance.finance.repository.FinanceRepository;
import com.pritish.finance.user.entity.User;
import com.pritish.finance.user.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinanceServiceImpl implements FinanceService {

    private final FinanceRepository financeRepository;
    private final UserRepository userRepository;

    @Override
    public FinanceResponse create(FinanceRequest request, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        FinanceRecord record = new FinanceRecord();
        record.setAmount(request.getAmount());
        record.setType(request.getType());
        record.setCategory(request.getCategory());
        record.setDate(request.getDate());
        record.setDescription(request.getDescription());
        record.setUser(user);

        return mapToResponse(financeRepository.save(record));
    }

    @Override
    public List<FinanceResponse> getAll() {
        return financeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FinanceResponse update(Long id, FinanceRequest request) {

        FinanceRecord record = financeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        record.setAmount(request.getAmount());
        record.setType(request.getType());
        record.setCategory(request.getCategory());
        record.setDate(request.getDate());
        record.setDescription(request.getDescription());

        return mapToResponse(financeRepository.save(record));
    }

    @Override
    public void delete(Long id) {
        financeRepository.deleteById(id);
    }

    @Override
    public List<FinanceResponse> filterByType(String type) {
        return financeRepository.findByType(type)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<FinanceResponse> filterByCategory(String category) {
        return financeRepository.findByCategory(category)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<FinanceResponse> filterByDateRange(String start, String end) {

        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        return financeRepository.findByDateBetween(startDate, endDate)
                .stream()
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