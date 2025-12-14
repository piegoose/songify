package pl.piegoose.songify.infrastructure.crud.song.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piegoose.songify.domain.crud.SongifyCrudeFacade;
import pl.piegoose.songify.domain.crud.dto.SongDto;
import pl.piegoose.songify.domain.crud.dto.SongRequestDto;
import pl.piegoose.songify.infrastructure.crud.song.controller.dto.request.PartiallyUpdateSongRequestDto;
import pl.piegoose.songify.infrastructure.crud.song.controller.dto.request.UpdateSongRequestDto;
import pl.piegoose.songify.infrastructure.crud.song.controller.dto.response.CreateSongResponseDto;
import pl.piegoose.songify.infrastructure.crud.song.controller.dto.response.DeleteSongResponseDto;
import pl.piegoose.songify.infrastructure.crud.song.controller.dto.response.GetAllSongsResponseDto;
import pl.piegoose.songify.infrastructure.crud.song.controller.dto.response.GetSongResponseDto;
import pl.piegoose.songify.infrastructure.crud.song.controller.dto.response.PartiallyUpdateSongResponseDto;
import pl.piegoose.songify.infrastructure.crud.song.controller.dto.response.UpdateSongResponseDto;


import java.util.List;

import static pl.piegoose.songify.infrastructure.crud.song.controller.SongControllerMapper.mapFromPartiallyUpdateSongRequestDtoToSong;
import static pl.piegoose.songify.infrastructure.crud.song.controller.SongControllerMapper.mapFromSongDtoToPartiallyUpdateSongResponseDto;
import static pl.piegoose.songify.infrastructure.crud.song.controller.SongControllerMapper.mapFromSongToCreateSongResponseDto;
import static pl.piegoose.songify.infrastructure.crud.song.controller.SongControllerMapper.mapFromSongToDeleteSongResponseDto;
import static pl.piegoose.songify.infrastructure.crud.song.controller.SongControllerMapper.mapFromSongToGetAllSongsResponseDto;
import static pl.piegoose.songify.infrastructure.crud.song.controller.SongControllerMapper.mapFromSongToGetSongResponseDto;
import static pl.piegoose.songify.infrastructure.crud.song.controller.SongControllerMapper.mapFromSongToUpdateSongResponseDto;
import static pl.piegoose.songify.infrastructure.crud.song.controller.SongControllerMapper.mapFromUpdateSongRequestDtoToSongDto;


@RestController
@Log4j2
@RequestMapping("/songs")
@AllArgsConstructor
public
class SongRestController {

    private final SongifyCrudeFacade songFacade;

    @GetMapping
    ResponseEntity<GetAllSongsResponseDto> getAllSongs(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
        List<SongDto> allSongs = songFacade.findAllSongs(pageable);
        GetAllSongsResponseDto response = mapFromSongToGetAllSongsResponseDto(allSongs);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    ResponseEntity<GetSongResponseDto> getSongById(@PathVariable Long id, @RequestHeader(required = false) String requestId) {
        log.info(requestId);
        SongDto song = songFacade.findSongDtoByIdSong(id);
        GetSongResponseDto response = mapFromSongToGetSongResponseDto(song);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    ResponseEntity<CreateSongResponseDto> postSong(@RequestBody @Valid SongRequestDto request) {
        SongDto savedSong = songFacade.addSong(request);
        CreateSongResponseDto body = mapFromSongToCreateSongResponseDto(savedSong);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<DeleteSongResponseDto> deleteSongByIdUsingPathVariable(@PathVariable Long id) {
        songFacade.deleteByIdSong(id);
        DeleteSongResponseDto body = mapFromSongToDeleteSongResponseDto(id);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    ResponseEntity<UpdateSongResponseDto> update(@PathVariable Long id,
                                                 @RequestBody @Valid UpdateSongRequestDto request) {
        SongDto newSongDto = mapFromUpdateSongRequestDtoToSongDto(request);
        songFacade.updateByIdSong(id, newSongDto);
        UpdateSongResponseDto body = mapFromSongToUpdateSongResponseDto(newSongDto);
        return ResponseEntity.ok(body);
    }

    @PatchMapping("/{id}")
    ResponseEntity<PartiallyUpdateSongResponseDto> partiallyUpdateSong(@PathVariable Long id,
                                                                       @RequestBody PartiallyUpdateSongRequestDto request) {
        SongDto updatedSong = mapFromPartiallyUpdateSongRequestDtoToSong(request);
        SongDto savedSong = songFacade.updatePartiallyByIdSong(id, updatedSong);
        PartiallyUpdateSongResponseDto body = mapFromSongDtoToPartiallyUpdateSongResponseDto(savedSong);
        return ResponseEntity.ok(body);
    }
}
