package pl.oli.cantor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.oli.cantor.model.Currency;
import pl.oli.cantor.model.dto.ExchangeRateResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExchangeService {

    public Double exchange(double amount, Currency from, Currency to) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("apikey", "41OBzW64QdYuC6CqLX7UMWpSWlw61zT7");
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ExchangeRateResponse> response = restTemplate.exchange("https://api.apilayer.com/fixer/convert?to=" + to + "&from=" + from + "&amount=" + amount, HttpMethod.GET, requestEntity, ExchangeRateResponse.class);
            return response.getBody().getResult();
        } catch (Exception e) {
            log.error("An error has occurred: ", e);
            throw new RuntimeException(e);
        }
    }
}
