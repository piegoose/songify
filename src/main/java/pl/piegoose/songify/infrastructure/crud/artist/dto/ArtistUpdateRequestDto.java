package pl.piegoose.songify.infrastructure.crud.artist.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ArtistUpdateRequestDto(
        @NotNull(message = "newArtistsName must not be null")
        @NotEmpty(message = "newArtistsName must not be empty")
        String newArtistsName

) {
}
