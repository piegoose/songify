package pl.piegoose.songify.infrastructure.crud.song.controller;



import org.springframework.http.HttpStatus;
import pl.piegoose.songify.domain.crud.song.dto.SongDto;
import pl.piegoose.songify.infrastructure.crud.song.controller.dto.request.CreateSongRequestDto;
import pl.piegoose.songify.infrastructure.crud.song.controller.dto.request.PartiallyUpdateSongRequestDto;
import pl.piegoose.songify.infrastructure.crud.song.controller.dto.request.UpdateSongRequestDto;
import pl.piegoose.songify.infrastructure.crud.song.controller.dto.response.CreateSongResponseDto;
import pl.piegoose.songify.infrastructure.crud.song.controller.dto.response.DeleteSongResponseDto;
import pl.piegoose.songify.infrastructure.crud.song.controller.dto.response.GetAllSongsResponseDto;
import pl.piegoose.songify.infrastructure.crud.song.controller.dto.response.GetSongResponseDto;
import pl.piegoose.songify.infrastructure.crud.song.controller.dto.response.PartiallyUpdateSongResponseDto;
import pl.piegoose.songify.infrastructure.crud.song.controller.dto.response.UpdateSongResponseDto;

import java.util.List;

class SongControllerMapper {

    static SongDto mapFromCreateSongRequestDtoToSongDto(CreateSongRequestDto dto) {
        return SongDto
                .builder()
                .name(dto.songName())
                .build();
    }

    static SongDto mapFromUpdateSongRequestDtoToSongDto(UpdateSongRequestDto dto) {
        return SongDto
                .builder()
                .name(dto.songName())
                .build();
    }

    static SongDto mapFromPartiallyUpdateSongRequestDtoToSong(PartiallyUpdateSongRequestDto dto) {
        return SongDto
                .builder()
                .name(dto.songName())
                .build();
    }

    static CreateSongResponseDto mapFromSongToCreateSongResponseDto(SongDto songDto) {
        return new CreateSongResponseDto(songDto);
    }

    static DeleteSongResponseDto mapFromSongToDeleteSongResponseDto(Long id) {
        return new DeleteSongResponseDto("You deleted song with id: " + id, HttpStatus.OK);
    }

    static UpdateSongResponseDto mapFromSongToUpdateSongResponseDto(SongDto newSong) {
        return new UpdateSongResponseDto(newSong.name(), "testt");
    }

    static PartiallyUpdateSongResponseDto mapFromSongDtoToPartiallyUpdateSongResponseDto(SongDto songDto) {
        return new PartiallyUpdateSongResponseDto(songDto);
    }

    static GetSongResponseDto mapFromSongToGetSongResponseDto(SongDto songDto) {
        return new GetSongResponseDto(songDto);
    }

    static GetAllSongsResponseDto mapFromSongToGetAllSongsResponseDto(List<SongDto> songs) {
        return new GetAllSongsResponseDto(songs);
    }
}
