package pl.oli.cantor.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.oli.cantor.model.Currency;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {
    private Double amount;
    private Integer userId;
    private Currency from;
    private Currency to;
}
