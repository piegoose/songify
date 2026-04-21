package pl.piegoose.songify.infrastructure.usercrud.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
class RegisterController {

    private final UserDetailsManager userDetailsManager;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> register(@RequestBody RegisterUserRequestDto dto) {
        String password = dto.password();
        String username = dto.username();
        userDetailsManager.createUser(User.builder()
                .username(username)
                .password(password)
                .build());
        return ResponseEntity.ok(new RegisterUserResponseDto("Created User"));
    }

}
