package pl.piegoose.songify.song.domain.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.piegoose.songify.song.domain.repository.SongRepository;

@Service
@Log4j2
@AllArgsConstructor
public class SongDeleter {

    private final SongRepository songRepository;
    private final SongRetriever songRetriever;

    public void deleteById (Long id) {
        songRetriever.findSongById(id);
        log.info("Song with id: {}deleted", id);
        songRepository.deleteById(id);

    }
}
