package com.victoria.wisedemo.api;

import com.victoria.wisedemo.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService svc;

    public PaymentController(PaymentService svc) {
        this.svc = svc;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentDto create(@RequestBody @Valid CreatePaymentRequest req) {
        return svc.create(req);
    }

    @GetMapping("/user/{userId}")
    public List<PaymentDto> getByUser(@PathVariable UUID userId) {
        return svc.findByUser(userId);
    }

    @GetMapping
    public List<PaymentDto> getAll() {
        return svc.findAll();
}

}
