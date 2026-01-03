package pl.piegoose.songify.domain.crud;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

class
InMemoryAlbumrepository implements AlbumRepository {
    @Override
    public Album save(final Album album) {
        return null;
    }

    @Override
    public Optional<Album> findById(final Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Album> findByIdAndSongsAndArtists(final Long id) {
        return Optional.empty();
    }

    @Override
    public Set<Album> findAllAlbumsByArtistsId(final Long id) {
        return Set.of();
    }

    @Override
    public int deleteByIdIn(final Collection<Long> ids) {
        return 0;
    }
}
