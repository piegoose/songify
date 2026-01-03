package pl.piegoose.songify.domain.crud;

class InMemoryGenreRepository implements GenreRepository {
    @Override
    public Genre save(final Genre genre) {
        return null;
    }

    @Override
    public int deleteGenreById(final Long id) {
        return 0;
    }
}
