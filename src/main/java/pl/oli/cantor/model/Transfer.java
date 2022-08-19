package pl.oli.cantor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import pl.oli.cantor.model.dto.TransferRequest;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double amount;

    @CreationTimestamp
    private LocalDateTime transferDate;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private Currency fromCurrency;

    @Enumerated(EnumType.STRING)
    private Currency toCurrency;

    @Enumerated(EnumType.STRING)
    private TransferType transferType;

    public static Transfer from(TransferRequest request, User user, TransferType transferType) {
        return new Transfer(null, request.getAmount(), null, user, request.getFrom(), request.getTo(), transferType);
    }

}
