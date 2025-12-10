package pl.piegoose.songify.infrastructure.error;

import org.springframework.http.HttpStatus;

public record ErrorSongResponseDto(String message, HttpStatus status) {
}
