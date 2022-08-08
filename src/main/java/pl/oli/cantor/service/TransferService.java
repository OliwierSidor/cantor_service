package pl.oli.cantor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.oli.cantor.exception.BadRequestException;
import pl.oli.cantor.exception.NotFoundException;
import pl.oli.cantor.model.Account;
import pl.oli.cantor.model.Transfer;
import pl.oli.cantor.model.TransferType;
import pl.oli.cantor.model.dto.TransferRequest;
import pl.oli.cantor.repository.AccountRepository;
import pl.oli.cantor.repository.TransferRepository;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransferService {
    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;

    public void transferIn(TransferRequest request) {
        if (request.getAmount() < 0) {
            throw new BadRequestException("Amount can not be lesser then 0");
        }
        Optional<Account> optionalAccount = accountRepository.findAccountByCurrencyAndUserId(request.getTo(), request.getUserId());
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            Transfer transfer = Transfer.from(request, account.getUser(), TransferType.IN);
            account.setAmount(account.getAmount() + request.getAmount());
            transferRepository.save(transfer);
        } else {
            throw new NotFoundException("Account not found");
        }
    }

    public void transferOut(TransferRequest request) {
        if (request.getAmount() < 0) {
            throw new BadRequestException("Amount can not be lesser then 0");
        }
        Optional<Account> optionalAccount = accountRepository.findAccountByCurrencyAndUserId(request.getFrom(), request.getUserId());
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            if (account.getAmount() < request.getAmount()) {
                throw new BadRequestException("Not enough money");
            } else {
                Transfer transfer = Transfer.from(request, account.getUser(), TransferType.OUT);
                account.setAmount(account.getAmount() - request.getAmount());
                transferRepository.save(transfer);
            }
        } else {
            throw new NotFoundException("Account not found");
        }
    }

    public void transferBetween(TransferRequest request) {
        if (request.getAmount() < 0) {
            throw new BadRequestException("Amount can not be lesser then 0");
        }
        Optional<Account> optionalAccountFrom = accountRepository.findAccountByCurrencyAndUserId(request.getFrom(), request.getUserId());
        Optional<Account> optionalAccountTo = accountRepository.findAccountByCurrencyAndUserId(request.getTo(), request.getUserId());
        if(optionalAccountTo.isPresent() && optionalAccountFrom.isPresent()){
            Account accountFrom = optionalAccountFrom.get();
            Account accountTo = optionalAccountTo.get();
            if(accountFrom.getAmount() < request.getAmount()){
                throw new BadRequestException("Not enough money");
            } else {
                Transfer transfer = Transfer.from(request, accountFrom.getUser(), TransferType.BETWEEN);
                accountTo.setAmount(accountTo.getAmount() + request.getAmount());
                accountFrom.setAmount(accountFrom.getAmount() - request.getAmount());
                transferRepository.save(transfer);
               // TODO: przeliczanie walut
            }
        }
    }
}
