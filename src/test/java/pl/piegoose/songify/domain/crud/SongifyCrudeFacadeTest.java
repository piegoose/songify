package pl.piegoose.songify.domain.crud;

import org.junit.jupiter.api.Test;
import pl.piegoose.songify.domain.crud.dto.ArtistDto;
import pl.piegoose.songify.domain.crud.dto.ArtistRequestDto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class SongifyCrudeFacadeTest {

    SongifyCrudeFacade songifyCrudeFacade = SongifyCrudFacadeConfiguration.createSongifyCrude(
            new InMemorySongRepository(),
            new InMemoryGenreRepository(),
            new InMemoryArtistRepository(),
            new InMemoryAlbumrepository() {
            }
    );

    @Test
    public void should_add_artist_amigo_with_id_0_when_amigo_was_sent() {
        // given
        ArtistRequestDto shawnMendes = ArtistRequestDto.builder()
                .name("amigo")
                .build();
        // when
        ArtistDto response = songifyCrudeFacade.addArtist(shawnMendes);
        //then
        assertThat(response.id()).isEqualTo(0L);
        assertThat(response.name()).isEqualTo("amigo");
    }

    @Test
    public void should_add_return_correct_dto() {
        // given
        ArtistRequestDto shawnMendes = ArtistRequestDto.builder()
                .name("sample")
                .build();
        // when
        ArtistDto response = songifyCrudeFacade.addArtist(shawnMendes);
        //then
        assertThat(response.id()).isNotNull();
        assertThat(response.name()).isNotNull();
    }
}