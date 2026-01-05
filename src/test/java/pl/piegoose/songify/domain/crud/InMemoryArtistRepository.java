package pl.piegoose.songify.domain.crud;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class InMemoryArtistRepository implements ArtistRepository {

    Map<Long, Artist> db = new HashMap<>();
    AtomicInteger index = new AtomicInteger(0);

    @Override
    public Artist save(final Artist artist) {
        long index = this.index.getAndIncrement();
        db.put(index, artist);
        artist.setId(index);
        return artist;
    }

    @Override
    public Set<Artist> findAll() {
        return new HashSet<>(db.values());
    }

    @Override
    public Optional<Artist> findById(final Long artistId) {
        return Optional.empty();
    }

    @Override
    public int deleteById(final Long id) {
        return 0;
    }
}
