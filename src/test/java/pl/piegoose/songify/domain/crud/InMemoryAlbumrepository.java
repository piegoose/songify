package pl.piegoose.songify.domain.crud;

import org.checkerframework.checker.units.qual.A;
import pl.piegoose.songify.domain.crud.dto.AlbumInfo;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class InMemoryAlbumrepository implements AlbumRepository {
    Map<Long,Album> db = new HashMap<>();
    AtomicInteger index = new AtomicInteger(0);
    @Override
    public Album save(final Album album) {
        long index = this.index.getAndIncrement();
        db.put(index, album);
        album.setId(index);
        return album;
    }

    @Override
    public Optional<AlbumInfo> findAlbumByIdWithSongsAndArtists(final Long id) {
        Album album = db.get(id);
        AlbumInfoTestImpl albumInfoTest = new AlbumInfoTestImpl(album);
        return Optional.of(albumInfoTest);
    }

    public Set<Album> findAllAlbumsByArtistId(final Long artistId) {
        return db.values().stream()
                .filter(album -> album.getArtists().stream()
                        .anyMatch(a -> a.getId().equals(artistId)))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Album> findAll() {
        return new HashSet<>(db.values());
    }


    @Override
    public Optional<Album> findById(final Long id) {
        Album album = db.get(id);
        return Optional.ofNullable(album);
    }

    @Override
    public int deleteByIdIn(final Collection<Long> ids) {
        ids.forEach(
                id -> db.remove(id)
        );
        return 0;
    }
}
