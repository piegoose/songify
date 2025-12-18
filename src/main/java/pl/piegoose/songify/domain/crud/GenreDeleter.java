package pl.piegoose.songify.domain.crud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class GenreDeleter {

    private final GenreRepository genreRepository;


    void deleteById(Long id) {
        int i = genreRepository.deleteGenreById(id);
        if (i!=1)
        {
            throw new GenreWasNotDeletedException("genre id: " +id+" not deleted");
        }
    }
}
