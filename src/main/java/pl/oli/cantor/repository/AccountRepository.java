package pl.oli.cantor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.oli.cantor.model.Account;
import pl.oli.cantor.model.User;
import pl.oli.cantor.model.dto.CreateAccountRequest;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findAccountByCurrency(CreateAccountRequest createAccountRequest);
    Optional<Account> findByAmount(Double amount);
    List<Account> findAccountsByUser(User user);
}
