package pl.piegoose.songify.domain.songplayer;

import org.springframework.stereotype.Controller;


interface YoutubeHttpClient {
    String playSongByName(String name);
}
