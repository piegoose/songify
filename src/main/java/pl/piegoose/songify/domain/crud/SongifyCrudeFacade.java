package pl.piegoose.songify.domain.crud;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.piegoose.songify.domain.crud.dto.AlbumDto;
import pl.piegoose.songify.domain.crud.dto.AlbumDtoWithArtistsAndSongs;
import pl.piegoose.songify.domain.crud.dto.AlbumRequestDto;
import pl.piegoose.songify.domain.crud.dto.ArtistDto;
import pl.piegoose.songify.domain.crud.dto.ArtistRequestDto;
import pl.piegoose.songify.domain.crud.dto.GenreDto;
import pl.piegoose.songify.domain.crud.dto.GenreRequestDto;
import pl.piegoose.songify.domain.crud.dto.SongDto;
import pl.piegoose.songify.domain.crud.dto.SongRequestDto;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class SongifyCrudeFacade {

    private final SongRetriever songRetriever;
    private final SongUpdater songUpdater;
    private final SongDeleter songDeleter;
    private final SongAdder songAdder;
    private final ArtistAdder artistAdder;
    private final GenreAdder genreAdder;
    private final AlbumAdder albumAdder;
    private final ArtistRetriever artistRetriever;
    private final AlbumRepository albumRepository;
    private final AlbumRetriever albumRetriever;
    private final ArtistDeleter artistDeleter;
    private final ArtistAssingner artistAssingner;

    public AlbumDtoWithArtistsAndSongs findAlbumByIdWithArtistsAndSongs(Long id) {
        return albumRetriever.findAlbumByIdWithArtistsAndSongs(id);
    }

    public ArtistDto addArtist(ArtistRequestDto dto) {
        return artistAdder.addArtist(dto.name());
    }

    public GenreDto addGenre(GenreRequestDto dto) {
        return genreAdder.addGenre(dto.name());
    }

    public AlbumDto addAlbumWithSong(AlbumRequestDto dto) {
        return albumAdder.addAlbum(dto.songId(), dto.title(), dto.releaseDate());
    }

    public void addArtistsToAlbum(Long artistsId, Long albumId){
        artistAssingner.addArtistToAlbum(artistsId,albumId);

    }

    public SongDto addSong(final SongRequestDto dto) {
        return songAdder.addSong(dto);
    }

    public Set<ArtistDto> findAllArtist() {
        return artistRetriever.findAllArtists();

    }

    public void deleteArtistByIdWithartistsAndSongs(Long artistId) {
        artistDeleter.deleteArtistByIdWithArtistsAndSongs(artistId);
    }

    public List<SongDto> findAllSongs(Pageable pageable) {
        return songRetriever.findAll(pageable);
    }

    public void updateByIdSong(Long id, SongDto newSongDto) {
        songRetriever.existsById(id);
        // some domain validator
        Song songValidatedAndReadyToUpdate = new Song(newSongDto.name());
        // some domain validator ended checking
        songUpdater.updateById(id, songValidatedAndReadyToUpdate);
    }

    public SongDto updatePartiallyByIdSong(Long id, SongDto songFromRequest) {
        songRetriever.existsById(id);
        Song songFromDatabase = songRetriever.findSongById(id);
        Song toSave = new Song();
        if (songFromRequest.name() != null) {
            toSave.setName(songFromRequest.name());
        } else {
            toSave.setName(songFromDatabase.getName());
        }
//        todo
//        if (songFromRequest.getArtist() != null) {
//            builder.artist(songFromRequest.getArtist());
//        } else {
//            builder.artist(songFromDatabase.getArtist());
//        }
        songUpdater.updateById(id, toSave);
        return SongDto.builder()
                .id(toSave.getId())
                .name(toSave.getName())
                .build();

    }

    public void deleteByIdSong(Long id) {
        songRetriever.existsById(id);
        songDeleter.deleteById(id);
    }

    public void deleteByIdSongAndGenre(Long songId) {
        songDeleter.deleSongAndGenreById(songId);
    }

    public SongDto findSongDtoByIdSong(Long id) {
        return songRetriever.findSongDtoById(id);
    }
}
