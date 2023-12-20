package digitalhands.az.controller;

import digitalhands.az.entity.User;
import digitalhands.az.exception.UserNotFoundException;
import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.repository.UserRepository;
import digitalhands.az.request.ChangePasswordRequest;
import digitalhands.az.request.UserLoginRequest;
import digitalhands.az.request.UserSignUpRequest;
import digitalhands.az.response.AuthenticationResponse;
import digitalhands.az.response.UserResponse;
import digitalhands.az.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserSignUpRequest userSignUpRequest) {
        return userService.signUp(userSignUpRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest) {
        String jwt = userService.login(userLoginRequest);
        if (jwt == null) {
            return ResponseEntity.status(BAD_REQUEST).build();
        } else {
            User user = userRepository.getUserByEmail(userLoginRequest.getEmail()).orElseThrow(
                    () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
            if (Objects.nonNull(user)) {
                AuthenticationResponse response = new AuthenticationResponse();
                response.setJwtToken(jwt);
                response.setId(user.getId());
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.USER_NOT_FOUND);
        }
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable(name = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
    }

    @PostMapping("/changePassword/{userId}")
    public void changePassword(@RequestBody ChangePasswordRequest changePasswordRequest,
                               @PathVariable(name = "userId") Long userId) {
        userService.changePassword(changePasswordRequest, userId);
    }

}