package org.bankapi.service;

import lombok.Getter;
import org.bankapi.exceptions.EntityNotFoundException;
import org.bankapi.models.Account;
import org.bankapi.models.Payment;
import org.bankapi.models.PaymentStatus;
import org.bankapi.repository.AccountRepository;
import org.bankapi.repository.PaymentRepository;

import java.math.BigDecimal;

@Getter
public abstract class AccountService extends AbstractService<Account> {
    private final PaymentRepository paymentRepository;
    private final BigDecimal fee;

    protected AccountService(AccountRepository accountRepository, PaymentRepository paymentRepository,
                             BigDecimal fee) {
        super(accountRepository);

        this.paymentRepository = paymentRepository;

        this.fee = fee;
    }

    public void deposit(long accountId, BigDecimal amount) throws EntityNotFoundException {
        Account account = findById(accountId);

        account.setBalance(account.getBalance().add(amount));

        save(account);
    }

    public void withdraw(long senderAccountId, long receiverAccountId,
                         BigDecimal amount) throws EntityNotFoundException {
        Account sender = findById(senderAccountId);

        Account receiver = findById(receiverAccountId);

        sender.setBalance(sender.getBalance().subtract(amount));

        deposit(receiverAccountId, amount.subtract(amount.multiply(fee)));

        save(sender);

        Payment payment = new Payment(amount, PaymentStatus.PROCESSING, sender, receiver);

        paymentRepository.save(payment);
    }
}
