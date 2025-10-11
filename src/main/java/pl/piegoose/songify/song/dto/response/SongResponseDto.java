package pl.piegoose.songify.song.dto.response;

import pl.piegoose.songify.song.controller.Song;

import java.util.Map;

public record SongResponseDto(
        Map<Integer, Song> songs
) {
}
