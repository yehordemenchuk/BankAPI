package org.bankapi.controllers;

import org.bankapi.models.Payment;
import org.bankapi.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController extends AbstractController<Payment> {
    @Autowired
    public PaymentController(PaymentService service) {
        super(service);
    }
}
