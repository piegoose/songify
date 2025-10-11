package pl.piegoose.songify.song.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateSongRequestDto(@NotNull(message = "songName must be not null")
                                   @NotEmpty(message = "songName must be not empty") String songName,
                                   @NotNull(message = "artist must be not null")
                                   @NotEmpty(message = "artist must be not empty") String artist) {

}
