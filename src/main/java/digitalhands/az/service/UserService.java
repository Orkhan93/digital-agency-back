package digitalhands.az.service;

import digitalhands.az.entity.User;
import digitalhands.az.enums.UserRole;
import digitalhands.az.exception.IncorrectPasswordException;
import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.exception.UserNotFoundException;
import digitalhands.az.mappers.UserMapper;
import digitalhands.az.repository.UserRepository;
import digitalhands.az.request.ChangePasswordRequest;
import digitalhands.az.request.UserLoginRequest;
import digitalhands.az.request.UserSignUpRequest;
import digitalhands.az.response.UserResponse;
import digitalhands.az.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static digitalhands.az.constant.AgencyConstant.*;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final EncryptionService encryptionService;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> signUp(UserSignUpRequest userSignUpRequest) {
        if (validationSignUp(userSignUpRequest)) {
            User user = userRepository.findFirstByEmail(userSignUpRequest.getEmail());
            if (Objects.isNull(user)) {
                User saved = userMapper.fromUserSignUpRequestToModel(userSignUpRequest);
                saved.setPassword(encryptionService.encryptPassword(userSignUpRequest.getPassword()));
                saved.setUserRole(UserRole.ADMIN);
                return ResponseEntity.status(CREATED)
                        .body(userRepository.save(saved));
            } else {
                log.error("userSignUpRequest {}", userSignUpRequest);
                return ResponseEntity.status(BAD_REQUEST).body(USER_ALREADY_EXISTS);
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public String login(UserLoginRequest userLoginRequest) {
        Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(userLoginRequest.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (encryptionService.verifyPassword(userLoginRequest.getPassword(), user.getPassword())) {
                return jwtUtil.generateTokenTest(userLoginRequest.getEmail());
            } else
                return INVALID_DATA;
        }
        log.error("login {}", optionalUser);
        return BAD_CREDENTIALS;
    }

    public ResponseEntity<UserResponse> getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND + userId));
        return ResponseEntity.ok(userMapper.fromModelToResponse(user));
    }

    public void changePassword(ChangePasswordRequest changePasswordRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND + userId));
        if (!encryptionService.verifyPassword(changePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new IncorrectPasswordException(ErrorMessage.INCORRECT_PASSWORD);
        }
        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())) {
            throw new IncorrectPasswordException(ErrorMessage.NOT_MATCHES);
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(user);
        log.info("changePassword {}", user);
    }

    private boolean validationSignUp(UserSignUpRequest signUpRequest) {
        return signUpRequest.getName() != null && signUpRequest.getEmail() != null
                && signUpRequest.getUsername() != null && signUpRequest.getPassword() != null;
    }

}