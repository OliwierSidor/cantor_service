package pl.oli.cantor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.oli.cantor.model.dto.AccountDTO;
import pl.oli.cantor.model.dto.CreateAccountRequest;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Currency currency;
    private Double amount;

    @ManyToOne
    private User user;

    public static Account from(CreateAccountRequest request, User user) {
        return new Account(null, request.getCurrency(), 0D, user);
    }

    public AccountDTO mapToDTO() {
        return new AccountDTO(id, currency, amount);
    }
}
