package pl.piegoose.songify.infrastructure.crud.artist;

import pl.piegoose.songify.domain.crud.dto.ArtistDto;

import java.util.Set;

public record AllArtistsDto(Set<ArtistDto> artistDtos) {
}
