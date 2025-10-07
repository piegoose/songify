package pl.piegoose.songify.Dto;

import java.util.Map;

public record SongResponseDto(
        Map<Integer,String> songs
) {
}
