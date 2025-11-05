package pl.piegoose.songify.song.infrastructure.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import pl.piegoose.songify.song.domain.model.Song;
import pl.piegoose.songify.song.infrastructure.controller.dto.request.CreateSongRequestDto;
import pl.piegoose.songify.song.infrastructure.controller.dto.request.PartiallyUpdateSongRequestDto;
import pl.piegoose.songify.song.infrastructure.controller.dto.request.UpdateSongRequestDto;
import pl.piegoose.songify.song.infrastructure.controller.dto.response.*;

public class SongMapper {

    public static Song mapFromCreateSongRequestDtoToSong(CreateSongRequestDto dto) {
        return new Song(dto.songName(), dto.artist());
    }

    public static Song mapFromUpdateSongRequestDtoToSong(UpdateSongRequestDto dto) {
        return new Song(dto.songName(), dto.artist());
    }

    public static Song mapFromPartiallyUpdateSongRequestDtoToSong(PartiallyUpdateSongRequestDto dto) {
        return new Song(dto.songName(), dto.artist());
    }

    public static CreateSongResponseDto mapFromSongToCreateSongResponseDto(Song song) {
        return new CreateSongResponseDto(song);
    }

    public static DeleteSongResponseDto mapFromSongToDeleteSongResponseDto(Long id) {
        return new DeleteSongResponseDto("You deleted song with id: " + id, HttpStatus.OK);
    }

    public static UpdateSongResponseDto mapFromSongToUpdateSongResponseDto(Song newSong) {
        return new UpdateSongResponseDto(newSong.getName(), newSong.getArtist());
    }

    public static PartiallyUpdateSongResponseDto mapFromSongToPartiallyUpdateSongResponseDto(Song updatedSong) {
        return new PartiallyUpdateSongResponseDto(updatedSong);
    }

    public static GetSongResponseDto mapFromSongToGetSongResponseDto(Song song) {
        return new GetSongResponseDto(song);
    }

    public static GetAllSongsResponseDto mapFromSongToGetAllSongsResponseDto(List<Song> database) {
        return new GetAllSongsResponseDto(database);
    }
}