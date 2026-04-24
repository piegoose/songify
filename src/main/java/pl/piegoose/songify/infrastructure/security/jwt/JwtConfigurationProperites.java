package pl.piegoose.songify.infrastructure.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(value = "auth.jwt")
public record JwtConfigurationProperites(
        String secret,
        long expirationMinutes,
        String issuer
) {
}
