package org.bankapi.service;

import org.bankapi.repository.AccountRepository;
import org.bankapi.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RegularAccountService extends AccountService {
    @Autowired
    public RegularAccountService(AccountRepository accountRepository,
                                 PaymentRepository paymentRepository) {

        super(accountRepository, paymentRepository, BigDecimal.ZERO);
    }
}
