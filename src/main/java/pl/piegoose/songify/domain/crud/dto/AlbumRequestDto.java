package pl.piegoose.songify.domain.crud.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.Set;

@Builder
public record AlbumRequestDto(
        String title,
        Instant releaseDate,
        Set<Long> songIds
) {
}
