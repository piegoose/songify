package pl.piegoose.songify.infrastructure.crud.song.dto.response;

import org.springframework.http.HttpStatus;

public record DeleteSongResponseDto(String message, HttpStatus status) {
}
