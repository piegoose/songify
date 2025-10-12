package pl.piegoose.songify.song.domain.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.piegoose.songify.song.domain.model.Song;
import pl.piegoose.songify.song.domain.repository.SongRepository;

@Log4j2
@Service
public class SongAdder {

    private final SongRepository songRepository;

    @Autowired
    public SongAdder(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song addSong(Song song) {
        log.info("adding new song: " + song);
        // zapytanie do serwisu songs.com/validate?songName=
        songRepository.saveToDatabase(song);
        return song;

    }

}
