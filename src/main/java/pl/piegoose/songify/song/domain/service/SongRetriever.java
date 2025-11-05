package pl.piegoose.songify.song.domain.service;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.piegoose.songify.song.domain.model.Song;
import pl.piegoose.songify.song.domain.model.SongNotFoundException;
import pl.piegoose.songify.song.domain.repository.SongRepository;


import java.util.List;

@Log4j2
@Service
public class  SongRetriever {

    private final SongRepository songRepository;

    @Autowired
    public SongRetriever(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> findAll(Pageable pageable) {

        log.info("Retriving all songs: ");
        return songRepository.findAll(pageable);

    }


    public Song findSongById(Long id) {
        return songRepository.findSongById(id)
                .orElseThrow(()-> new SongNotFoundException("SongNotFoundException while accessing song"));
    }
    public void existById (Long id) {
        if(!songRepository.existsById(id)){
            throw new SongNotFoundException("SongNotFoundException while accessing song");
        }
    }


}
