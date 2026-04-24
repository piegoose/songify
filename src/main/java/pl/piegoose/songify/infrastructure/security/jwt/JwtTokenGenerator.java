package pl.piegoose.songify.infrastructure.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pl.piegoose.songify.infrastructure.security.SecurityUser;


import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
@RequiredArgsConstructor
class JwtTokenGenerator {

    private static final String ROLES_CLAIM_NAME = "roles";

    private final AuthenticationManager authenticationManager;
    private final Clock clock;
    private final JwtConfigurationProperites properties;

    public String authenticateAndGenerateToken(String username, String password) {
        UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authenticate);
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        Instant issuedAt = LocalDateTime.now(clock).toInstant(ZoneOffset.UTC);
        Instant expireAt = issuedAt.plus(Duration.ofMinutes(properties.expirationMinutes()));
        Algorithm algorithm = Algorithm.HMAC256(properties.secret());
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(issuedAt)
                .withExpiresAt(expireAt)
                .withIssuer(properties.issuer())
                .withClaim(ROLES_CLAIM_NAME, user.getAuthorietiesAsString())
                .sign(algorithm);
    }
}
