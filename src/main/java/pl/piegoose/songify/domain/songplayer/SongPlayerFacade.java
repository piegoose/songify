package pl.piegoose.songify.domain.songplayer;

import pl.piegoose.songify.domain.crud.SongifyCrudeFacade;
import pl.piegoose.songify.domain.crud.dto.SongDto;

public class SongPlayerFacade {

    private final SongifyCrudeFacade songifyCrudeFacade;
    private final YoutubeHttpClient youtubeHttpClient;

    SongPlayerFacade(final SongifyCrudeFacade songifyCrudeFacade, final YoutubeHttpClient youtubeHttpClient) {
        this.songifyCrudeFacade = songifyCrudeFacade;
        this.youtubeHttpClient = youtubeHttpClient;
    }

    public String playSongWithId(Long id) {
        SongDto songDtoByIdSong = songifyCrudeFacade.findSongDtoByIdSong(id);
        String name = songDtoByIdSong.name();
        String result = youtubeHttpClient.playSongByName(name);
        if(result.equals("sucess")){
            return result;
        }
        throw new RuntimeException("some error - result failed");
    }
}
