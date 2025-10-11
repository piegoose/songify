package pl.piegoose.songify.song.dto.response;

import org.springframework.http.HttpStatus;

public record DeleteSongResponseDto(String message,
                                    HttpStatus status) {
}
