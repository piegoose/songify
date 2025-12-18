package pl.piegoose.songify.domain.crud;

import jakarta.websocket.server.ServerEndpoint;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
class ArtistAssingner {
    private final ArtistRetriever artistRetriever;
    private final AlbumRetriever albumRetriever;

    void addArtistToAlbum(final Long artistsId, final Long albumId) {
        Artist artist = artistRetriever.findById(artistsId);
        Album album = albumRetriever.findById(albumId);
        artist.addAlbum(album);
    }
}
