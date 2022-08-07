package pl.oli.cantor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.oli.cantor.exception.BadRequestException;
import pl.oli.cantor.model.Account;
import pl.oli.cantor.model.dto.CreateAccountRequest;
import pl.oli.cantor.repository.AccountRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public void create(CreateAccountRequest request) {
        Optional<Account> optionalAccount = accountRepository.findAccountByCurrency(request);
        if (optionalAccount.isEmpty()) {
            accountRepository.save(request);
        } else {
            throw new BadRequestException("Account already exits");
        }

    }

    public void remove(Integer id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            if (accountRepository.findById(id).isEmpty()) {
                accountRepository.delete(account);
                log.info("Account delete");
            } else {
                log.info("This account cannot be delete because it's not empty");
                throw new BadRequestException("This account cannot be delete because it's not empty");
            }
        } else {
            throw new EntityNotFoundException("Account not found");
        }
    }
}

