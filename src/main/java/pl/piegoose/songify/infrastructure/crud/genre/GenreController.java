package pl.piegoose.songify.infrastructure.crud.genre;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piegoose.songify.domain.crud.SongifyCrudeFacade;
import pl.piegoose.songify.domain.crud.dto.ArtistDto;
import pl.piegoose.songify.domain.crud.dto.ArtistRequestDto;
import pl.piegoose.songify.domain.crud.dto.GenreDto;
import pl.piegoose.songify.domain.crud.dto.GenreRequestDto;

@AllArgsConstructor
@RestController
@RequestMapping("/genre")
class GenreController {
    private final SongifyCrudeFacade songifyCrudeFacade;


    @PostMapping
    ResponseEntity<GenreDto> postArtist(@RequestBody GenreRequestDto genreRequestDto) {
        GenreDto genreDto = songifyCrudeFacade.addGenre(genreRequestDto);
        return ResponseEntity.ok(genreDto);
    }
}
