package pl.oli.cantor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.oli.cantor.exception.AlreadyExistsException;
import pl.oli.cantor.exception.NotFoundException;
import pl.oli.cantor.model.Currency;
import pl.oli.cantor.model.User;
import pl.oli.cantor.model.UserStatus;
import pl.oli.cantor.model.dto.RegisterUserRequest;
import pl.oli.cantor.model.dto.UpdateUserRequest;
import pl.oli.cantor.model.dto.UserDTO;
import pl.oli.cantor.repository.AccountRepository;
import pl.oli.cantor.repository.UserRepository;
import pl.oli.cantor.repository.UserStatusRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserStatusRepository userStatusRepository;
    private final AccountRepository accountRepository;

    public void register(RegisterUserRequest request) {
        Optional<User> userOptional = userRepository.findByPesel(request.getPesel());
        if (userOptional.isPresent()) {
            throw new AlreadyExistsException("User already exists");
        } else {
            Optional<UserStatus> userStatusOptional = userStatusRepository.findByName("bronze");
            if (userStatusOptional.isEmpty()) {
                throw new NotFoundException("Status not found");
            } else {
                UserStatus newUserStatus = userStatusOptional.get();
                User newUser = User.from(request, newUserStatus);
                userRepository.save(newUser);
            }
        }
    }

    public List<UserDTO> listAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(User::mapToDTO).collect(Collectors.toList());
    }

    public void removeUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(userRepository.findByCurrency().isEmpty() && accountRepository.findByAmmount().isEmpty()){
                userRepository.delete(user);
            }
        }
    }

    public void updateUser(UpdateUserRequest request) {
        Integer id = request.getId();
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            User userToUpdate = optionalUser.get();
            if(userToUpdate.getName() != null){
                userToUpdate.setName(request.getName());
            } else if (userToUpdate.getSurname() != null) {
                userToUpdate.setSurname(request.getSurname());
            } else if (userToUpdate.getRole() != null) {
                userToUpdate.setRole(request.getRole());
            } else if (userToUpdate.getPassword() != null) {
                userToUpdate.setPassword(request.getPassword());
            } else if (userToUpdate.getUserStatus() != null) {
                userToUpdate.setUserStatus(request.getUserStatus());
            } else {
                throw new NotFoundException("You are not editing any field");
            }
            userRepository.save(userToUpdate);
            log.info("User has been updated");
        } else {
            throw new NotFoundException("User not found");
        }
    }
}
