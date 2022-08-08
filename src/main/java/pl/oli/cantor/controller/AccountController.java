package pl.oli.cantor.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.oli.cantor.model.dto.AccountDTO;
import pl.oli.cantor.model.dto.CreateAccountRequest;
import pl.oli.cantor.service.AccountService;

import java.util.List;

@Slf4j
@RequestMapping("/api/account")
@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestBody CreateAccountRequest request) {
        log.info("Creating account");
        accountService.create(request);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAccount(@PathVariable(name = "id") Integer id) {
        log.info("Deleting account");
        accountService.remove(id);
    }

    @GetMapping("/list/{id}")
    public List<AccountDTO> accountDTOList(@PathVariable(name= "id") Integer userId) {
        return accountService.list(userId);
    }
}
