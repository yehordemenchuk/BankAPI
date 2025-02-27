package org.bankapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class BankApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApiApplication.class, args);
    }

}
