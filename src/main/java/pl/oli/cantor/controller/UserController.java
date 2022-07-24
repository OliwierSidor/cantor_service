package pl.oli.cantor.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.oli.cantor.model.dto.RegisterUserRequest;
import pl.oli.cantor.model.dto.UpdateUserRequest;
import pl.oli.cantor.model.dto.UserDTO;
import pl.oli.cantor.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(RegisterUserRequest request) {
        log.info("Registering User");
        userService.register(request);
    }

    @GetMapping
    public List<UserDTO> userList() {
        log.info("Listing all users");
        return userService.listAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUser(@PathVariable(name = "id") Long id) {
        log.info("Removing User");
        userService.removeUser(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUser(@PathVariable(name = "id") Long id, @RequestBody UpdateUserRequest request) {
        log.info("Updating user");
        userService.updateUser(id, request);
    }
}
