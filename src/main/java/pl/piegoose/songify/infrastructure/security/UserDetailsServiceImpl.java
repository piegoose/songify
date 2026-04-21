package pl.piegoose.songify.infrastructure.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import pl.piegoose.songify.domain.usercrud.User;
import pl.piegoose.songify.domain.usercrud.UserRepository;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
class UserDetailsServiceImpl implements UserDetailsManager {

    private static final String DEFAULT_USER_ROLE = "ROLE_USER";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findFirstByEmail(username)
                .map(user -> new SecurityUser(user))
                .orElseThrow(() -> new UsernameNotFoundException("not found user: " + username));
    }

    @Override
    public void createUser(final UserDetails user) {
        if (userExists(user.getUsername())) {
            System.out.println("Cannot save user - already exists");
            throw new RuntimeException("Cannot save user - already exists");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        User createUser = new User(
                user.getUsername(),
                encodedPassword,
                true,
                List.of(DEFAULT_USER_ROLE).toArray(new String[0])
        );
        User savedUser = userRepository.save(createUser);
        log.info("User with id " + savedUser.getId() + " created");
        // sent email confirmation
    }

    @Override
    public void updateUser(final UserDetails user) {
    }

    @Override
    public void deleteUser(final String username) {
    }

    @Override
    public void changePassword(final String oldPassword, final String newPassword) {
    }

    @Override
    public boolean userExists(final String username) {
        return userRepository.existsByEmail(username);
    }
}