package pl.piegoose.songify.song.domain.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.piegoose.songify.song.domain.model.Song;
import pl.piegoose.songify.song.domain.repository.SongRepository;

import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Service
public class SongRetriever {

    private final SongRepository songRepository;

    @Autowired
    public SongRetriever(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Map<Integer, Song> findAll() {

        log.info("retriving all songs: ");
        return songRepository.findAll();

    }

    public Map<Integer, Song> findfindAllLimitedBy(Integer limit) {

        return songRepository.findAll()
                .entrySet()
                .stream()
                .limit(limit)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

}
