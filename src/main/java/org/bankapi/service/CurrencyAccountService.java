package org.bankapi.service;

import org.bankapi.repository.AccountRepository;
import org.bankapi.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CurrencyAccountService extends AccountService {
    @Autowired
    public CurrencyAccountService(AccountRepository accountRepository,
                                  PaymentRepository paymentRepository) {

        super(accountRepository, paymentRepository, BigDecimal.valueOf(0.15));
    }
}
