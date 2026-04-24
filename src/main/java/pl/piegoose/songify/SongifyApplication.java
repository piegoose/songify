package pl.piegoose.songify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.piegoose.songify.infrastructure.security.jwt.JwtConfigurationProperites;

@SpringBootApplication
@EnableConfigurationProperties(value = {JwtConfigurationProperites.class})
public class SongifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SongifyApplication.class, args);


    }

}
