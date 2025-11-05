package pl.piegoose.songify.song.domain.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.piegoose.songify.song.domain.model.Song;
import pl.piegoose.songify.song.domain.repository.SongRepository;

import java.util.Optional;

@Service
@Log4j2
@Transactional
@AllArgsConstructor
public class SongUpdater {
    private final SongRepository songRepository;
    private final SongRetriever songRetriever;


    public void updateById(Long id, Song newSong) {
        songRetriever.existById(id);
        songRepository.updateById(id, newSong);

    }
}
