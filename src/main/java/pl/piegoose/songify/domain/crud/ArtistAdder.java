package pl.piegoose.songify.domain.crud;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.piegoose.songify.domain.crud.dto.ArtistDto;
import pl.piegoose.songify.domain.crud.dto.ArtistRequestDto;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.UUID;
@Log4j2
@Service
@AllArgsConstructor
class ArtistAdder {

    private final ArtistRepository artistRepository;
    private final AlbumAdder albumAdder;

    ArtistDto addArtist(final String name) {
        Artist save = saveArtist(name);
        return new ArtistDto(save.getId(), save.getName());
    }

    private Artist saveArtist(final String name) {
        Artist artist = new Artist(name);
        return artistRepository.save(artist);
    }

    ArtistDto addArtistWithDefaultAlbumAndSong(final ArtistRequestDto dto) {
        String artistName = dto.name();
        Artist save = saveArtistWithDefaultAlbumAndSong(artistName);
        return new ArtistDto(save.getId(), save.getName());
    }

    private Artist saveArtistWithDefaultAlbumAndSong(final String name) {
        Artist artist = new Artist(name);

        Album album = new Album();
        album.setTitle("default-album:" + UUID.randomUUID());
        album.setReleaseDate(LocalDateTime.now().toInstant(ZoneOffset.UTC));

        Song song = new Song("default-song-name: " + UUID.randomUUID());

        album.addSongToAlbum(song);
        artist.addAlbum(album); // <----
        return artistRepository.save(artist);

//        better way is using AlbumAdder, SongAdder and specific methods, for me, not using cascade!
        // Album album = albumAdder.addAlbum(
        //                "default-album:" + UUID.randomUUID(),
        //                LocalDateTime.now().toInstant(ZoneOffset.UTC));
        //        Song song = songAdder.addSongAndGetEntity(new SongRequestDto(
        //                "default-song-name: " + UUID.randomUUID(),
        //                LocalDateTime.now().toInstant(ZoneOffset.UTC),
        //                0L,
        //                SongLanguageDto.OTHER
        //        ));
    }
}