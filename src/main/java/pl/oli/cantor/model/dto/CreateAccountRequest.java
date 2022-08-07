package pl.oli.cantor.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.oli.cantor.model.Currency;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequest {

    private Currency currency;
    private Double amount;
}
