package pl.piegoose.songify.infrastructure.crud.artist;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piegoose.songify.domain.crud.SongifyCrudeFacade;
import pl.piegoose.songify.domain.crud.dto.ArtistDto;
import pl.piegoose.songify.domain.crud.dto.ArtistRequestDto;

import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/artist")
class ArtistController {
    private final SongifyCrudeFacade songifyCrudeFacade;


    @PostMapping
    ResponseEntity<ArtistDto> postArtist(@RequestBody ArtistRequestDto artistRequestDto) {
        ArtistDto artistDto = songifyCrudeFacade.addArtist(artistRequestDto);
        return ResponseEntity.ok(artistDto);
    }

    @GetMapping
    ResponseEntity<AllArtistsDto> getArtist() {
        Set<ArtistDto> artists = songifyCrudeFacade.findAllArtist();
        return ResponseEntity.ok(new AllArtistsDto(artists));
    }
    @DeleteMapping("/{artistsId}")
    ResponseEntity<String> deleteArtistsWithAllAlbumsAndSongs(@PathVariable Long artistsId) {
        songifyCrudeFacade.deleteArtistByIdWithartistsAndSongs(artistsId);
        return ResponseEntity.ok("Propably all deleteted :) ");
    }

    @PutMapping("/{artistsId}/{albumId}")
    ResponseEntity<String> addArtistsToAlbum(@PathVariable Long artistsId,Long albumId) {
        songifyCrudeFacade.addArtistsToAlbum(artistsId,albumId);
        return ResponseEntity.ok("Artist with id: "+artistsId+" assingned to album with id: "+albumId+" :)");
    }

}
