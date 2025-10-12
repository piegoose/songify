package pl.piegoose.songify.song.infrastructure.controller.dto.request;

public record PartiallyUpdateSongRequestDto(
        String songName,
        String artist) {
}
