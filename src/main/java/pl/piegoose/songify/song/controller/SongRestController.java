package pl.piegoose.songify.song.controller;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.piegoose.songify.song.dto.DeleteSongResponseDto;
import pl.piegoose.songify.song.dto.SingleSongResponseDto;
import pl.piegoose.songify.song.dto.SongRequestDto;
import pl.piegoose.songify.song.dto.SongResponseDto;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Log4j2

public class SongRestController {

    Map<Integer, String> database = new HashMap<>(Map.of(
            1, "Shawn Mendes - Song1",
            2, "Ariana Grande",
            3, "Lose Yourself",
            4, "Without Me",
            5, "Candy Shop",
            6, "Many Men",
            7, "N.W.A",
            8, "F*** the Police",
            9, "California Love",
            10, "Still D.R.E."
    ));


    @GetMapping("/songs")
    public ResponseEntity<SongResponseDto> getAllSongs() {
        SongResponseDto responseDto = new SongResponseDto(database);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<SingleSongResponseDto> getSongById(@PathVariable Integer id, @RequestHeader(required = false) String requestId) {
        log.info(requestId);
        String song = database.get(id);
        if (song == null) {
            return ResponseEntity.notFound().build();
        }
        SingleSongResponseDto responseDto = new SingleSongResponseDto(song);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/songsbyparam")
    public ResponseEntity<SongResponseDto> getAllSongsParam(@RequestParam(required = false) Integer limit) {
        if (limit != null) {
            Map<Integer, String> limitedMap = database.entrySet()
                    .stream()
                    .limit(limit)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            SongResponseDto responseDto = new SongResponseDto(limitedMap);
            return ResponseEntity.ok(responseDto);
        }
        SongResponseDto responseDto = new SongResponseDto(database);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/songs")
    public ResponseEntity<SingleSongResponseDto> postSong(@RequestBody @Valid SongRequestDto requestDto) {
        String songName = requestDto.songName();
        log.info("Song added" + songName);
        database.put(database.size() + 1, songName);
        return ResponseEntity.ok(new SingleSongResponseDto(songName));
    }

    @DeleteMapping("/song/{id}")
    public ResponseEntity<DeleteSongResponseDto> deleteSongByIdUsingPathVariable(@PathVariable Integer id) {
        if(!database.containsKey(id))
        {
            throw new RuntimeException("Song with id "+ id + "not found");
        }
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ErrorDeleteSongResponseDto("Song with id " + id + " not found ",HttpStatus.NOT_FOUND));}

        database.remove(id);
        return ResponseEntity.ok(new DeleteSongResponseDto("You deleted song with id: " + id,HttpStatus.OK));

    }
}
