package pl.piegoose.songify.infrastructure.crud.artist;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

record ArtistUpdateRequestDto(
        @NotNull(message = "newArtistsName must not be null")
        @NotEmpty(message = "newArtistsName must not be empty")
        String newArtistsName

) {
}
