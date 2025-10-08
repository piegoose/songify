package pl.piegoose.songify.song;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SongRequestDto(
        @NotNull(message = "songName must be not null")
        @NotEmpty(message = "songName must be not empty") String songName
) {
}
