package pl.piegoose.songify.domain.crud;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.data.domain.Pageable;
import pl.piegoose.songify.domain.crud.dto.AlbumDto;
import pl.piegoose.songify.domain.crud.dto.AlbumInfo;
import pl.piegoose.songify.domain.crud.dto.AlbumRequestDto;
import pl.piegoose.songify.domain.crud.dto.ArtistDto;
import pl.piegoose.songify.domain.crud.dto.ArtistRequestDto;
import pl.piegoose.songify.domain.crud.dto.SongDto;
import pl.piegoose.songify.domain.crud.dto.SongLanguageDto;
import pl.piegoose.songify.domain.crud.dto.SongRequestDto;


import java.util.Collections;
import java.util.List;
import java.util.Set;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertTrue;


class SongifyCrudeFacadeTest {

    SongifyCrudeFacade songifyCrudFacade = SongifyCrudFacadeConfiguration.createSongifyCrude(
            new InMemorySongRepository(),
            new InMemoryGenreRepository(),
            new InMemoryArtistRepository(),
            new InMemoryAlbumrepository() {
            }
    );

    //GWT


    @Test
    @DisplayName("Should add artist amigo with id: 0, when 'amigo' was sent.")
    public void should_add_artist_amigo_with_id_0_when_amigo_was_sent() {
        // given
        ArtistRequestDto shawnMendes = ArtistRequestDto.builder()
                .name("amigo")
                .build();
        Set<ArtistDto> allArtist = songifyCrudFacade.findAllArtists(Pageable.unpaged());
        assertTrue(allArtist.isEmpty());
        // when
        ArtistDto response = songifyCrudFacade.addArtist(shawnMendes);
        //then
        assertThat(response.id()).isEqualTo(0L);
        assertThat(response.name()).isEqualTo("amigo");
        int size = songifyCrudFacade.findAllArtists(Pageable.unpaged()).size();
        assertThat(size).isEqualTo(1);
    }

    @Test
    @DisplayName("Should throw exception : 'Artist not found', when ID was: 0.")
    public void should_throw_exception_artists_not_found_when_id_was_zero() {
        // given
        assertThat(songifyCrudFacade.findAllArtists(Pageable.unpaged())).isEmpty();
        // when
        Throwable throwable = catchThrowable(() -> songifyCrudFacade.deleteArtistByIdWithAlbumsAndSongs(0L));
        //then
        assertThat(throwable).isInstanceOf(RuntimeException.class);
        assertThat(throwable.getMessage()).isEqualTo("artist with id: " + 0 + " not found..");
    }

    @Test
    @DisplayName("Should delete artist by id When he have no albums")
    public void should_delete_artist_by_id_when_he_have_no_albums() {
        // given
        ArtistRequestDto shawnMendes = ArtistRequestDto.builder()
                .name("shawn mendes")
                .build();
        Long artistId = songifyCrudFacade.addArtist(shawnMendes).id();
        assertThat(songifyCrudFacade.findAlbumsByArtistId(artistId)).isEmpty();
        // when
        songifyCrudFacade.deleteArtistByIdWithAlbumsAndSongs(artistId);
        // then
        assertThat(songifyCrudFacade.findAllArtists(Pageable.unpaged())).isEmpty();
    }

    @Test
    @DisplayName("Should delete artist with album and songs by id When artist had one album and he was the only artist in album")
    public void should_delete_artist_with_album_and_songs_by_id_when_artist_had_one_album_and_he_was_the_only_artist_in_album() {
        // given
        ArtistRequestDto shawnMendes = ArtistRequestDto.builder()
                .name("shawn mendes")
                .build();
        Long artistId = songifyCrudFacade.addArtist(shawnMendes).id();
        SongRequestDto song = SongRequestDto.builder()
                .name("song1")
                .language(SongLanguageDto.ENGLISH)
                .build();
        SongDto songDto = songifyCrudFacade.addSong(song);
        Long songId = songDto.id();
        AlbumDto albumDto = songifyCrudFacade.addAlbumWithSong(AlbumRequestDto
                .builder()
                .songIds(Set.of(songId))
                .title("album title 1")
                .build());
        Long albumId = albumDto.id();
        songifyCrudFacade.addArtistsToAlbum(artistId, albumId);
        assertThat(songifyCrudFacade.findAlbumsByArtistId(artistId).size()).isEqualTo(1);
        assertThat(songifyCrudFacade.countArtistsByAlbumId(albumId)).isEqualTo(1);
        // when
        songifyCrudFacade.deleteArtistByIdWithAlbumsAndSongs(artistId);
        // then
        assertThat(songifyCrudFacade.findAllArtists(Pageable.unpaged())).isEmpty();
        Throwable throwable = catchThrowable(() -> songifyCrudFacade.findSongDtoByIdSong(songId));
        assertThat(throwable).isInstanceOf(SongNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Song with id 0 not found");
        Throwable throwable2 = catchThrowable(() -> songifyCrudFacade.findAlbumById(albumId));
        assertThat(throwable2).isInstanceOf(AlbumNotFoundException.class);
        assertThat(throwable2.getMessage()).isEqualTo("Album with id: 0 not found");
    }


    @Test
    @DisplayName("Should delete only artist from album by id, when there were more than 1 artist in album.")
    public void should_delete_only_artist_from_album_by_id_when_there_were_more_than_1_artist_in_album() {

    }

    @Test
    public void should_add_album_with_song() {
        //given
        SongRequestDto songRequestDto = SongRequestDto.builder()
                .name("song1")
                .language(SongLanguageDto.ENGLISH)
                .build();
        SongDto songDto = songifyCrudFacade.addSong(songRequestDto);

        AlbumRequestDto album = AlbumRequestDto
                .builder()
                .songIds(Collections.singleton(songDto.id()))
                .title("album title 1")
                .build();
        assertThat(songifyCrudFacade.findAllAlbums()).isEmpty();
        //when
        AlbumDto albumDto = songifyCrudFacade.addAlbumWithSong(album);
        //then
        assertThat(songifyCrudFacade.findAllAlbums()).isNotEmpty();
        AlbumInfo albumByIdWithArtistsAndSongs = songifyCrudFacade.findAlbumByIdWithArtistsAndSongs(albumDto.id());
        Set<AlbumInfo.SongInfo> songs = albumByIdWithArtistsAndSongs.getSongs();
        assertTrue(songs.stream().anyMatch(song -> song.getId().equals(songDto.id())));
    }

    @Test
    public void should_add_song() {
        //TODO
        //given
        SongRequestDto song = SongRequestDto.builder()
                .name("song1")
                .language(SongLanguageDto.ENGLISH)
                .build();
        assertThat(songifyCrudFacade.findAllSongs(Pageable.unpaged()).isEmpty());
        //when
        songifyCrudFacade.addSong(song);
        //then
        List<SongDto> allSongs = songifyCrudFacade.findAllSongs(Pageable.unpaged());
        assertThat(allSongs)
                .extracting(SongDto::id)
                .containsExactly(1L);

    }
    @Test
    @DisplayName("should add artist to album")
    public void should_add_artist_to_album()
    {
        //given
        ArtistRequestDto shawnMendes = ArtistRequestDto.builder()
                .name("shawn mendes")
                .build();
        Long artistId = songifyCrudFacade.addArtist(shawnMendes).id();
        SongRequestDto song = SongRequestDto.builder()
                .name("song1")
                .language(SongLanguageDto.ENGLISH)
                .build();
        SongDto songDto = songifyCrudFacade.addSong(song);
        Long songId = songDto.id();
        AlbumDto albumDto = songifyCrudFacade.addAlbumWithSong(AlbumRequestDto
                .builder()
                .songIds(Set.of(songId))
                .title("album title 1")
                .build());
        Long albumId = albumDto.id();
        assertThat(songifyCrudFacade.findAlbumsByArtistId(artistId)).isEmpty();
        //when
        songifyCrudFacade.addArtistsToAlbum(artistId, albumId);
        //then
        Set<AlbumDto> albumsByArtistId = songifyCrudFacade.findAlbumsByArtistId(artistId);
        assertThat(albumsByArtistId).isNotEmpty();
        assertThat(albumsByArtistId)
                .extracting(AlbumDto::id)
                .containsExactly(albumId);
    }

    @Test
    @DisplayName("should retrun album by id")
    public void should_retrun_album_by_id()
    {
        //given
        SongRequestDto song = SongRequestDto.builder()
                .name("song1")
                .language(SongLanguageDto.ENGLISH)
                .build();
        SongDto songDto = songifyCrudFacade.addSong(song);
        Long songId = songDto.id();
        AlbumDto albumDto = songifyCrudFacade.addAlbumWithSong(AlbumRequestDto
                .builder()
                .songIds(Set.of(songId))
                .title("album title 1")
                .build());
        Long albumId = albumDto.id();

        //when
        AlbumDto albumById = songifyCrudFacade.findAlbumById(albumId);
        //then
        assertThat(albumById)
                .extracting(AlbumDto::id, AlbumDto::name)
                .containsExactly(albumId, "album title 1");
    }
    @Test
    @DisplayName("should throw exception when album not found by id")
    public void should_throw_exception_when_album_not_found_by_id(){
        // given
        assertThat(songifyCrudFacade.findAllAlbums()).isEmpty();
        // when
        Throwable throwable = catchThrowable(() -> songifyCrudFacade.findAlbumById(55L));
        //then
        assertThat(throwable).isInstanceOf(AlbumNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Album with id: 55 not found");
    }

    @Test
    @DisplayName("should throw exception when song not found by id")
    public void should_throw_exception_when_song_not_found_by_id(){
        // given
        assertThat(songifyCrudFacade.findAllSongs(Pageable.unpaged())).isEmpty();
        // when
        Throwable throwable = catchThrowable(() -> songifyCrudFacade.findSongDtoByIdSong(55L));
        //then
        assertThat(throwable).isInstanceOf(SongNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Song with id 55 not found");
    }
    @Test
    @DisplayName("Should Delete Only Artist From Album By When There Were More Than One Artist In Album")
    public void should_delete_only_artist_from_album_by_when_there_were_more_than_one_artist_in_album(){
        // given
        ArtistRequestDto shawnMendes = ArtistRequestDto.builder()
                .name("shawn mendes")
                .build();
        ArtistRequestDto camilaCabello = ArtistRequestDto.builder()
                .name("camila cabello")
                .build();
        Long artistId = songifyCrudFacade.addArtist(shawnMendes).id();
        Long artistId2 = songifyCrudFacade.addArtist(camilaCabello).id();
        SongRequestDto song = SongRequestDto.builder()
                .name("Seniorita")
                .language(SongLanguageDto.ENGLISH)
                .build();
        SongDto songDto = songifyCrudFacade.addSong(song);
        Long songId = songDto.id();
        AlbumDto albumDto = songifyCrudFacade.addAlbumWithSong(AlbumRequestDto
                .builder()
                .songIds(Set.of(songId))
                .title("Album with Seniorita")
                .build());
        Long albumId = albumDto.id();
        songifyCrudFacade.addArtistsToAlbum(artistId, albumId);
        songifyCrudFacade.addArtistsToAlbum(artistId2, albumId);
        assertThat(songifyCrudFacade.countArtistsByAlbumId(albumId)).isEqualTo(2);
        // when
        songifyCrudFacade.deleteArtistByIdWithAlbumsAndSongs(artistId);
        // then
        AlbumInfo album = songifyCrudFacade.findAlbumByIdWithArtistsAndSongs(albumId);
        Set<AlbumInfo.ArtistInfo> artists = album.getArtists();
        assertThat(artists)
                .extracting("id")
                .containsOnly(artistId2);
    }
    @Test
    @DisplayName("should delete artist with all albums and all songs by id when artist was the only artist in albums")
    public void should_delete_artist_with_all_albums_and_all_songs_by_id_when_artist_was_the_only_artist_in_albums()
    {

    }

}