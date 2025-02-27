package org.bankapi.models;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class CurrencyAccount extends Account {
    @NonNull
    private String currency;
}
