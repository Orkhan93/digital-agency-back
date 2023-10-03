package digitalhands.az.controller;

import digitalhands.az.request.ChangePasswordRequest;
import digitalhands.az.request.UserLoginRequest;
import digitalhands.az.request.UserSignUpRequest;
import digitalhands.az.response.AuthenticationResponse;
import digitalhands.az.response.UserResponse;
import digitalhands.az.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserSignUpRequest userSignUpRequest) {
        return userService.signUp(userSignUpRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        String jwt = userService.login(userLoginRequest);
        if (jwt == null) {
            return ResponseEntity.status(BAD_REQUEST).build();
        } else {
            AuthenticationResponse response = new AuthenticationResponse();
            response.setJwtToken(jwt);
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable(name = "userId") Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/changePassword/{userId}")
    public void changePassword(@RequestBody ChangePasswordRequest changePasswordRequest,
                               @PathVariable(name = "userId") Long userId) {
        userService.changePassword(changePasswordRequest, userId);
    }

}