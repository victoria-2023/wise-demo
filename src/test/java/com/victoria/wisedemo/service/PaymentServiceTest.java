package com.victoria.wisedemo.service;

import com.victoria.wisedemo.api.CreatePaymentRequest;
import com.victoria.wisedemo.api.PaymentDto;
import com.victoria.wisedemo.domain.Payment;
import com.victoria.wisedemo.repo.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    @Mock
    private PaymentRepository repo;

    @InjectMocks
    private PaymentService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_shouldSaveAndReturnDto() {
        UUID userId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CreatePaymentRequest req = new CreatePaymentRequest(userId, new BigDecimal("42.00"), "USD", "EUR");

        // Stub save to return a Payment with ID 5
        when(repo.save(any(Payment.class))).thenAnswer(inv -> {
            Payment p = inv.getArgument(0);
            return new Payment(p.getUserId(), p.getAmount(), p.getCurrencyFrom(), p.getCurrencyTo()) {
                @Override public Long getId() { return 5L; }
            };
        });

        PaymentDto dto = service.create(req);

        assertThat(dto.id()).isEqualTo(5L);
        assertThat(dto.userId()).isEqualTo(userId);
        assertThat(dto.amount()).isEqualByComparingTo(req.amount());
        verify(repo).save(any(Payment.class));
    }

    @Test
    void findByUser_shouldReturnMappedDtos() {
        UUID userId = UUID.randomUUID();
        Payment p1 = new Payment(userId, new BigDecimal("10"), "A", "B");
        Payment p2 = new Payment(userId, new BigDecimal("20"), "C", "D");

        when(repo.findByUserId(userId)).thenReturn(List.of(p1, p2));

        List<PaymentDto> dtos = service.findByUser(userId);

        assertThat(dtos).hasSize(2)
                        .extracting(PaymentDto::userId)
                        .containsOnly(userId);
        verify(repo).findByUserId(userId);
    }

    @Test
    void findAll_shouldReturnMappedDtos() {
        Payment p = new Payment(UUID.randomUUID(), new BigDecimal("5"), "X", "Y");
        when(repo.findAll()).thenReturn(List.of(p));

        var dtos = service.findAll();

        assertThat(dtos).hasSize(1)
                        .first()
                        .satisfies(d -> assertThat(d.amount()).isEqualByComparingTo(p.getAmount()));
        verify(repo).findAll();
    }
}
