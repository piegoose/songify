package pl.piegoose.songify.song.domain.repository;



import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.piegoose.songify.song.domain.model.Song;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends org.springframework.data.repository.Repository<Song, Long> {

    Song save(Song song);

    @Query("SELECT s FROM Song s")
    List<Song> findAll(Pageable pageable);

    @Query("SELECT s From Song s where s.id = :id")
    Optional<Song> findSongById(Long id);

    @Modifying
    @Query("DELETE FROM Song s where  s.id = :id")
    void deleteById(Long id);

    @Modifying
    @Query("update Song s set s.name = :#{#newSong.name},s.artist = :#{#newSong.artist} where s.id=:id")
    void updateById(Long id, Song newSong);

    boolean existsById(Long id);
}
