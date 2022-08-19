package pl.oli.cantor.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.oli.cantor.model.dto.TransferRequest;
import pl.oli.cantor.service.TransferService;

@Slf4j
@RestController
@RequestMapping("/api/transfer")
@RequiredArgsConstructor
public class TransferController {
    private final TransferService transferService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void transferIn(@RequestBody TransferRequest request) {
        log.info("Making transfer");
        transferService.transferIn(request);
    }

    @PostMapping("/withdraw")
    @ResponseStatus(HttpStatus.CREATED)
    public void transferOut(@RequestBody TransferRequest request){
        log.info("Withdrawing money!!!!!");
        transferService.transferOut(request);
    }

    @PostMapping("/exchange")
    @ResponseStatus(HttpStatus.CREATED)
    public void transferBetween(@RequestBody TransferRequest request){
        log.info("Transferring between accounts");
        transferService.transferBetween(request);
    }
}
