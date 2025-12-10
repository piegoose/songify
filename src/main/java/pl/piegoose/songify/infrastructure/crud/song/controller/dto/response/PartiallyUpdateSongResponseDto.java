package pl.piegoose.songify.infrastructure.crud.song.dto.response;


import pl.piegoose.songify.domain.crud.dto.SongDto;

public record PartiallyUpdateSongResponseDto(SongDto updatedSong) {
}
