package com.victoria.wisedemo.service;

import com.victoria.wisedemo.domain.Payment;
import com.victoria.wisedemo.repo.PaymentRepository;
import com.victoria.wisedemo.api.PaymentDto;
import com.victoria.wisedemo.api.CreatePaymentRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    private final PaymentRepository repo;

    public PaymentService(PaymentRepository repo) {
        this.repo = repo;
    }

    public PaymentDto create(CreatePaymentRequest req) {
        Payment payment = new Payment(
            req.userId(),
            req.amount(),
            req.from(),
            req.to()
        );
        Payment saved = repo.save(payment);
        return mapToDto(saved);
    }

    public List<PaymentDto> findByUser(UUID userId) {
        return repo.findByUserId(userId).stream()
                   .map(this::mapToDto)
                   .collect(Collectors.toList());
    }

    public List<PaymentDto> findAll() {
    return repo.findAll()
               .stream()
               .map(this::mapToDto)
               .collect(Collectors.toList());
}

    private PaymentDto mapToDto(Payment p) {
        return new PaymentDto(
            p.getId(),
            p.getUserId(),
            p.getAmount(),
            p.getCurrencyFrom(),
            p.getCurrencyTo(),
            p.getCreatedAt()
        );
    }
}
