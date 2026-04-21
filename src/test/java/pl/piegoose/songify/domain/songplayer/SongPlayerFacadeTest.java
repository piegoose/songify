package pl.piegoose.songify.domain.songplayer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.piegoose.songify.domain.crud.SongifyCrudeFacade;
import pl.piegoose.songify.domain.crud.dto.SongDto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SongPlayerFacadeTest {

    SongifyCrudeFacade songifyCrudeFacade = mock(SongifyCrudeFacade.class);
    YoutubeHttpClient youtubeHttpClient = mock(YoutubeHttpClient.class);

    SongPlayerFacade songPlayerFacade = new SongPlayerFacade(
            songifyCrudeFacade,
            youtubeHttpClient
    );

    @Test
    @DisplayName("should return success when played song with id")
    void should_return_success_when_played_song_with_id() {
        when(songifyCrudeFacade.findSongDtoByIdSong(anyLong()))
                .thenReturn(SongDto.builder().id(1L).name("mockito").build());

        when(youtubeHttpClient.playSongByName(anyString()))
                .thenReturn("success"); // or "sucess" if your facade expects the typo

        String result = songPlayerFacade.playSongWithId(1L);

        assertThat(result).isEqualTo("success");
    }

}