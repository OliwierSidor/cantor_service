package pl.oli.cantor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.oli.cantor.model.Account;
import pl.oli.cantor.model.Currency;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findAccountByCurrencyAndUserId(Currency currency, Integer userId);
    List<Account> findAccountsByUserId(Integer userId);
}
