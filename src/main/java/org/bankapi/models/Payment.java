package org.bankapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class Payment {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private BigDecimal amount;

    @NonNull
    private PaymentStatus paymentStatus;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    @JsonBackReference
    private Account senderAccount;

    @NonNull
    @OneToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Account receiverAccount;
}
