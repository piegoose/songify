package pl.piegoose.songify.infrastructure.crud.song.controller.dto.response;


import pl.piegoose.songify.domain.crud.song.dto.SongDto;

import java.util.List;

public record GetAllSongsResponseDto(List<SongDto> songs) {
}
