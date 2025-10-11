package pl.piegoose.songify.song.dto.request;

public record PartiallyUpdateSongRequestDto(
        String songName,
        String artist) {
}
