package pl.piegoose.songify.infrastructure.crud.album;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piegoose.songify.domain.crud.SongifyCrudeFacade;
import pl.piegoose.songify.domain.crud.dto.AlbumDto;
import pl.piegoose.songify.domain.crud.dto.AlbumDtoWithArtistsAndSongs;
import pl.piegoose.songify.domain.crud.dto.AlbumRequestDto;

@AllArgsConstructor
@RestController
@RequestMapping("/album")
class AlbumController {
    private final SongifyCrudeFacade songifyCrudeFacade;


    @PostMapping
    ResponseEntity<AlbumDto> postAlbum(@RequestBody AlbumRequestDto albumRequestDto) {
        AlbumDto albumDto = songifyCrudeFacade.addAlbumWithSong(albumRequestDto);
        return ResponseEntity.ok(albumDto);
    }

    @GetMapping("/{albumId}")
    ResponseEntity<AlbumDtoWithArtistsAndSongs> getAlbumWithArtistsAndSongs(@PathVariable Long albumId){
        AlbumDtoWithArtistsAndSongs albumByIdWithArtistsAndSongs = songifyCrudeFacade.findAlbumByIdWithArtistsAndSongs(albumId);
        return ResponseEntity.ok(albumByIdWithArtistsAndSongs);
    }

}
