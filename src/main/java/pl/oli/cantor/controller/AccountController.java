package pl.oli.cantor.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.oli.cantor.model.dto.CreateAccountRequest;
import pl.oli.cantor.service.AccountService;

@Slf4j
@RequestMapping("/api/account")
@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(CreateAccountRequest request) {
        log.info("Creating account");
        accountService.create(request);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAccount(@PathVariable(name = "id") Integer id){
        log.info("Deleting account");
        accountService.remove(id);
    }
}
