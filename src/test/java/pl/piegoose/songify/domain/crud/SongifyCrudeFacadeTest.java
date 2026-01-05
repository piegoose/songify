package pl.piegoose.songify.domain.crud;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.data.domain.Pageable;
import pl.piegoose.songify.domain.crud.dto.ArtistDto;
import pl.piegoose.songify.domain.crud.dto.ArtistRequestDto;


import java.util.Set;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertTrue;


class SongifyCrudeFacadeTest {

    SongifyCrudeFacade songifyCrudeFacade = SongifyCrudFacadeConfiguration.createSongifyCrude(
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
        Set<ArtistDto> allArtist = songifyCrudeFacade.findAllArtist(Pageable.unpaged());
        assertTrue(allArtist.isEmpty());
        // when
        ArtistDto response = songifyCrudeFacade.addArtist(shawnMendes);
        //then
        assertThat(response.id()).isEqualTo(0L);
        assertThat(response.name()).isEqualTo("amigo");
        int size = songifyCrudeFacade.findAllArtist(Pageable.unpaged()).size();
        assertThat(size).isEqualTo(1);
    }

    @Test
    @DisplayName("Should throw exception : 'Artist not found', when ID was: 0.")
    public void should_throw_exception_artists_not_found_when_id_was_zero() {
        // given
        assertThat(songifyCrudeFacade.findAllArtist(Pageable.unpaged())).isEmpty();
        // when
        Throwable throwable = catchThrowable(() -> songifyCrudeFacade.deleteArtistByIdWithArtistsAndSongs(0L));
        //then
        assertThat(throwable).isInstanceOf(RuntimeException.class);
        assertThat(throwable.getMessage()).isEqualTo("artist with id: "+ 0 +" not found..");
    }

}