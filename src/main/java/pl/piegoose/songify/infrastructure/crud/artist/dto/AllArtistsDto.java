package pl.piegoose.songify.infrastructure.crud.artist.dto;

import pl.piegoose.songify.domain.crud.dto.ArtistDto;

import java.util.Set;

public record AllArtistsDto(Set<ArtistDto> artistDtos) {
}
