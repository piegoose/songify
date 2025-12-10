package pl.piegoose.songify.infrastructure.crud.song.dto.response;


import pl.piegoose.songify.domain.crud.dto.SongDto;

import java.util.List;

public record GetAllSongsResponseDto(List<SongDto> songs) {
}
