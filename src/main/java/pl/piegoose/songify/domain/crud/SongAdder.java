package pl.piegoose.songify.domain.crud;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.piegoose.songify.domain.crud.dto.GenreDto;
import pl.piegoose.songify.domain.crud.dto.SongDto;
import pl.piegoose.songify.domain.crud.dto.SongLanguageDto;
import pl.piegoose.songify.domain.crud.dto.SongRequestDto;

@Log4j2
@Service
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class SongAdder {

    private final SongRepository songRepository;
    private final GenreAssigner genreAssigner;
    SongDto addSong(final SongRequestDto songDto) {
        SongLanguageDto language = songDto.language();
        SongLanguage songLanguage = SongLanguage.valueOf(language.name());
        Song song = new Song(songDto.name(), songDto.releaseDate(), songDto.duration(), songLanguage);
        log.info("adding new song: " + song);
        log.info("SongAdder repo instance: {}", System.identityHashCode(songRepository));
        Song save = songRepository.save(song);
        genreAssigner.assignDefaultGenreToSong(song.getId());
        return new SongDto(save.getId(), save.getName(), new GenreDto(save.getGenre().getId(), save.getGenre().getName()));
    }

    Song addSongAndGetEntity(final SongRequestDto songRequestDto) {
        SongLanguageDto language = songRequestDto.language();
        SongLanguage songLanguage = SongLanguage.valueOf(language.name());
        Song song = new Song(songRequestDto.name(), songRequestDto.releaseDate(), songRequestDto.duration(), songLanguage);
        log.info("adding new song: " + song);
        return songRepository.save(song);
    }
}
