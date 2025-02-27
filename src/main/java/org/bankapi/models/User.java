package org.bankapi.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Table(name = "users")
public class User {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String surname;

    @NonNull
    private Integer age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Account> accounts;
}
