package pl.piegoose.songify.song.infrastructure.controller.dto.response;

import pl.piegoose.songify.song.domain.model.Song;

import java.util.Map;

public record GetAllSongsResponseDto(
        Map<Integer, Song> songs
) {
}
