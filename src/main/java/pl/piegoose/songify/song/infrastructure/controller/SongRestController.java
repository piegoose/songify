package pl.piegoose.songify.song.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.piegoose.songify.song.domain.model.Song;
import pl.piegoose.songify.song.domain.model.SongNotFoundException;
import pl.piegoose.songify.song.infrastructure.controller.dto.request.CreateSongRequestDto;
import pl.piegoose.songify.song.infrastructure.controller.dto.request.PartiallyUpdateSongRequestDto;
import pl.piegoose.songify.song.infrastructure.controller.dto.request.UpdateSongRequestDto;
import pl.piegoose.songify.song.infrastructure.controller.dto.response.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequestMapping("/songs")
public class SongRestController {

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


    @GetMapping
    public ResponseEntity<GetAllSongsResponseDto> getAllSongs(@RequestParam(required = false) Integer limit) {
        if (limit != null) {
            Map<Integer, Song> limitedMap = database.entrySet()
                    .stream()
                    .limit(limit)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            GetAllSongsResponseDto responseDto = new GetAllSongsResponseDto(limitedMap);
            return ResponseEntity.ok(responseDto);
        }
        GetAllSongsResponseDto responseDto = new GetAllSongsResponseDto(database);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<GetSongResponseDto> getSongById(@PathVariable Integer id, @RequestHeader(required = false) String requestId) {
        log.info(requestId);
        if (!database.containsKey(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
        Song song = database.get(id);
        GetSongResponseDto responseDto = new GetSongResponseDto(song);
        return ResponseEntity.ok(responseDto);
    }


    @PostMapping
    public ResponseEntity<CreateSongResponseDto> postSong(@RequestBody @Valid CreateSongRequestDto request) {
        // 1. Mappowanie z CreateSongRequestDto na obiekt Domenowy (Song)
        Song song = SongMapper.mapFromCreateSongRequestDtoToSong(request);
        // 2. Warstwa logiki biznesowej / serwisow domenowych: wysiwetlamy infromacje
        log.info("Song added" + song);
        // 3. Wasrtswe bazodanowa: zapisujemy do bazy danych
        database.put(database.size() + 1, song);
        // 4. mappowanie z obiektu domenowego (Song) na DTO Create Song Request Dto
        CreateSongResponseDto body = SongMapper.mapFromSongToCreateResponseDto(song);
        return ResponseEntity.ok(body);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteSongResponseDto> deleteSongByIdUsingPathVariable(@PathVariable Integer id) {
        if (!database.containsKey(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
        database.remove(id);
        return ResponseEntity.ok(new DeleteSongResponseDto("You deleted song with id: " + id, HttpStatus.OK));

    }

    @PutMapping("/{id}") //akt calosci
    public ResponseEntity<UpdateSongResponseDto> update(@PathVariable Integer id, @Valid @RequestBody UpdateSongRequestDto request) {
        if (!database.containsKey(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
        String newSongName = request.songName();
        String newArtist = request.artist();
        Song newSong = new Song(newSongName, newArtist);
        Song oldSong = database.put(id, newSong);
        log.info("\n Updated song with id: " + id +
                " \n" + " Old Song name: " + oldSong.name() +
                "\n" + " Oldsong artist: " + oldSong.artist() +
                " \n" + " New song name: " + newSong.name() +
                "\n" + " New song artist: " + newSong.artist());
        return ResponseEntity.ok(new UpdateSongResponseDto(newSong.name(), newSong.artist()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PartiallyUpdateSongResponseDto> partiallyUpdateSong(@PathVariable Integer id,
                                                                              @RequestBody PartiallyUpdateSongRequestDto request) {

        if (!database.containsKey(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
        Song songFromDatabase = database.get(id);
        Song.SongBuilder builder = Song.builder();
        if (request.songName() != null) {
            builder.name(request.songName());
            log.info("Partially updated song name");
        } else {
            builder.name(songFromDatabase.name());
        }
        if (request.artist() != null) {
            builder.artist(request.artist());
            log.info("Partially updated artist name");

        } else {
            builder.artist(songFromDatabase.artist());
        }
        Song updatedSong = builder.build();
        database.put(id, updatedSong);
        return ResponseEntity.ok(new PartiallyUpdateSongResponseDto(updatedSong));

    }


}
