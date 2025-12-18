package pl.piegoose.songify.domain.crud;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
class ArtistDeleter {
    private final ArtistRepository artistRepository;
    private final ArtistRetriever artistRetriever;
    private final AlbumRetriever albumRetriever;
    private final AlbumDeleter albumDeleter;
    private final SongDeleter songDeleter;
    private final AlbumRepository albumRepository;

    void deleteArtistByIdWithArtistsAndSongs(final Long artistId) {
        Artist artist = artistRetriever.findById(artistId);
        Set<Album> artistAlbums = albumRetriever.findAlbumsByArtistId(artist.getId());
        if (artistAlbums.isEmpty()) {
            log.info("Artist with id: " + artistId + " has 0 albums...");
            artistRepository.deleteById(artistId);
            return;
        }

        artistAlbums.stream()
                .filter(album -> album.getArtists().size() >= 2)
                .forEach(album -> album.removeArtists(artist));

        Set<Album> albumsWithOnlyOneArtist = artistAlbums.stream()
                .filter(album -> album.getArtists().size() == 1)
                .collect(Collectors.toSet());

        Set<Long> allSongsIdsFromAllAlbumsWherewasOnlyThisArtist = albumsWithOnlyOneArtist
                .stream()
                .flatMap(album -> album.getSongs().stream())
                .map(Song::getId)
                .collect(Collectors.toSet());
        songDeleter.deleteAllSongsById(allSongsIdsFromAllAlbumsWherewasOnlyThisArtist);

        Set<Long> albumsIds = albumsWithOnlyOneArtist.stream()
                .map(Album::getId)
                .collect(Collectors.toSet());
        albumDeleter.deleteAllAlbumsByIds(albumsIds);


    }
}
