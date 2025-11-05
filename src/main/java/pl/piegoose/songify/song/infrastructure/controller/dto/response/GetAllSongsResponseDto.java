package pl.piegoose.songify.song.infrastructure.controller.dto.response;

import pl.piegoose.songify.song.domain.model.Song;

import java.util.List;
import java.util.Map;

public record GetAllSongsResponseDto(
        List<Song> songs
) {
}
