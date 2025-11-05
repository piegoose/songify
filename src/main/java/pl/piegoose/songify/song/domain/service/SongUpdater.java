package pl.piegoose.songify.song.domain.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.piegoose.songify.song.domain.model.Song;
import pl.piegoose.songify.song.domain.repository.SongRepository;

@Service
@Log4j2
@Transactional
@AllArgsConstructor
public class SongUpdater {
    private final SongRetriever songRetriever;

    public void updateById(Long id, Song newSong) {
        Song songById = songRetriever.findSongById(id);
        songById.setName(newSong.getName());
        songById.setArtist(newSong.getArtist());
    }

    public Song updatePartiallyById(Long id, Song songFromRequest) {
        Song songFromDatabase = songRetriever.findSongById(id);
        if (songFromRequest.getName() != null) {
            songFromDatabase.setName(songFromRequest.getName());
        }
        if (songFromRequest.getArtist() != null) {
            songFromDatabase.setArtist(songFromRequest.getArtist());
        }
        return songFromDatabase;
    }
}
