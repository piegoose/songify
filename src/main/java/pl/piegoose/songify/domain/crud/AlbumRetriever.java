package pl.piegoose.songify.domain.crud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.piegoose.songify.domain.crud.dto.AlbumDto;
import pl.piegoose.songify.domain.crud.dto.AlbumDtoWithArtistsAndSongs;
import pl.piegoose.songify.domain.crud.dto.ArtistDto;
import pl.piegoose.songify.domain.crud.dto.GenreDto;
import pl.piegoose.songify.domain.crud.dto.SongDto;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class AlbumRetriever {

    private final AlbumRepository albumRepository;

    AlbumDtoWithArtistsAndSongs findAlbumByIdWithArtistsAndSongs(final Long id) {
        Album album = albumRepository.findByIdAndSongsAndArtists(id)
                .orElseThrow(() -> new AlbumNotFoundException("Album with id: " + id + " not found.."));

        Set<Artist> artists = album.getArtists();
        Set<Song> songs = album.getSongs();

        AlbumDto albumDto = new AlbumDto(album.getId(), album.getTitle());

        Set<ArtistDto> artistDto = artists.stream()
                .map(artist -> new ArtistDto(
                        artist.getId(),
                        artist.getName()
                ))
                .collect(Collectors.toSet());
        Set<SongDto> songsDto = songs.stream()
                .map(song -> new SongDto(
                        song.getId(),
                        song.getName(),new GenreDto(song.getId(), song.getName())
                ))
                .collect(Collectors.toSet());

        return new AlbumDtoWithArtistsAndSongs(
                albumDto, artistDto, songsDto
        );
    }
}
