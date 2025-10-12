package pl.piegoose.songify.song.infrastructure.controller.dto.response;

import org.springframework.http.HttpStatus;

public record DeleteSongResponseDto(String message,
                                    HttpStatus status) {
}
