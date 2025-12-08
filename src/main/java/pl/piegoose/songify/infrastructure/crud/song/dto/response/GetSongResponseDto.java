package pl.piegoose.songify.infrastructure.crud.song.controller.dto.response;


import pl.piegoose.songify.domain.crud.song.dto.SongDto;

public record GetSongResponseDto(SongDto song) {
}
