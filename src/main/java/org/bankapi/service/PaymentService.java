package org.bankapi.service;

import org.bankapi.models.Payment;
import org.bankapi.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService extends AbstractService<Payment> {
    @Autowired
    public PaymentService(PaymentRepository repository) {
        super(repository);
    }
}
