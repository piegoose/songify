package pl.piegoose.songify.song.dto.response;

import pl.piegoose.songify.song.controller.Song;

public record PartiallyUpdateSongResponseDto(Song updatedSong){
}
