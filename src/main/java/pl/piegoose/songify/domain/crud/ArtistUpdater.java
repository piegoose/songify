package pl.piegoose.songify.domain.crud;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import pl.piegoose.songify.domain.crud.dto.AlbumDto;
import pl.piegoose.songify.domain.crud.dto.ArtistDto;

@AllArgsConstructor
@Service
class ArtistUpdater {


    private final ArtistRetriever artistRetriever;

    ArtistDto updateArtistNameById(final Long artistId, final String name) {
        Artist artist = artistRetriever.findById(artistId);
        artist.setName(name);
        return new ArtistDto(artist.getId(),artist.getName());
    }
}


