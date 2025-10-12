package pl.piegoose.songify.song.infrastructure.controller.dto.response;

import pl.piegoose.songify.song.domain.model.Song;

public record CreateSongResponseDto(Song song) {
}
