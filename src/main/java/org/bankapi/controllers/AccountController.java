package org.bankapi.controllers;

import org.bankapi.models.Account;
import org.bankapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController implements Controller<Account> {
    private final Map<String, AccountService> accountServices;

    @Autowired
    public AccountController(List<AccountService> accountServicesList) {
        this.accountServices = accountServicesList
                                                .stream()
                                                .collect(Collectors
                                                        .toMap(
                                                                service ->
                                                                                service
                                                                                .getClass()
                                                                                .getSuperclass()
                                                                                .getSimpleName(),
                                                                              Function.identity()
                                                        )
                                                );
    }

    private String getServiceType(String type) {
        return Arrays
                .stream(type.split("_"))
                .map(string -> string.substring(0, 1).toUpperCase()
                        + string.substring(1).toLowerCase())
                .collect(Collectors.joining()) + "Service";
    }

    public void save(@RequestBody Account account) {
        accountServices.get("RegularAccountService").save(account);
    }

    public Account findById(@PathVariable long id) {
        return accountServices.get("RegularAccountService").findById(id);
    }

    public List<Account> findAll() {
        return accountServices.get("RegularAccountService").findAll();
    }

    @PutMapping("/deposit")
    public void deposit(@RequestParam BigDecimal amount,
                        @RequestParam long accountId) {

        accountServices.get("RegularAccountService").deposit(accountId, amount);
    }

    @PutMapping("/withdraw")
    public void withdraw(@RequestParam BigDecimal amount,
                         @RequestParam String type,
                         @RequestParam long senderAccountId,
                         @RequestParam long receiverAccountId) {

        System.out.println(getServiceType(type));

        accountServices.get(getServiceType(type)).withdraw(senderAccountId, receiverAccountId, amount);
    }

    public void deleteById(@PathVariable long id) {
        accountServices.get("RegularAccountService").deleteById(id);
    }
}
