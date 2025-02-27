package org.bankapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RegularAccount.class, name = "regular_account"),
        @JsonSubTypes.Type(value = CurrencyAccount.class, name = "currency_account")
})
public class Account {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private Long number;

    @NonNull
    private BigDecimal balance;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "senderAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Payment> payments;
}
