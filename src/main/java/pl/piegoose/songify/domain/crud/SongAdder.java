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

    SongDto addSong(final SongRequestDto songDto) {
        SongLanguageDto language = songDto.language();
        SongLanguage songLanguage = SongLanguage.valueOf(language.name());
        Song song = new Song(songDto.name(), songDto.releaseDate(), songDto.duration(), songLanguage);
        log.info("adding new song: " + songDto);
        songRepository.save(song);
        return new SongDto(song.getId(), song.getName(), new GenreDto(song.getGenre().getId(), song.getGenre().getName()));
    }

    Song addSongAndGetEntity(final SongRequestDto songRequestDto) {
        SongLanguageDto language = songRequestDto.language();
        SongLanguage songLanguage = SongLanguage.valueOf(language.name());
        Song song = new Song(songRequestDto.name(), songRequestDto.releaseDate(), songRequestDto.duration(), songLanguage);
        log.info("adding new song: " + song);
        return songRepository.save(song);
    }
}
