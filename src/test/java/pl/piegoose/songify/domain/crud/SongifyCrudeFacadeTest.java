package pl.piegoose.songify.domain.crud;

import org.junit.jupiter.api.Test;

import org.springframework.data.domain.Pageable;
import pl.piegoose.songify.domain.crud.dto.ArtistDto;
import pl.piegoose.songify.domain.crud.dto.ArtistRequestDto;


import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

}