package pl.piegoose.songify.domain.crud.dto;

import lombok.Builder;

@Builder
public record ArtistRequestDto(String name) {
}
