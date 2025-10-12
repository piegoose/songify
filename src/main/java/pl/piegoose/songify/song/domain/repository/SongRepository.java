package pl.piegoose.songify.song.domain.repository;

import org.springframework.stereotype.Repository;
import pl.piegoose.songify.song.domain.model.Song;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SongRepository {
    Map<Integer, Song> database = new HashMap<>(Map.of(
            1, new Song("Stitches", "Shawn Mendes"),
            2, new Song("Costam", "Ariana Grande"),
            3, new Song("Lose Yourself", "Eminem"),
            4, new Song("Without Me", "Halsey"),
            5, new Song("Candy Shop", "50 Cent"),
            6, new Song("Many Men", "50 Cent"),
            7, new Song("Straight Outta Compton", "N.W.A"),
            8, new Song("F*** the Police", "N.W.A"),
            9, new Song("California Love", "2Pac feat. Dr. Dre"),
            10, new Song("Still D.R.E.", "Dr. Dre feat. Snoop Dogg")
    ));

    public Song saveToDatabase(Song song) {
        database.put(database.size() + 1, song);
        return song;
    }

    public Map<Integer, Song> findAll() {
        return database;
    }
}
