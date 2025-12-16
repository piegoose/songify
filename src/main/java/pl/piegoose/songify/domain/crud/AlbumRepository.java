package pl.piegoose.songify.domain.crud;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

interface AlbumRepository extends Repository<Album,Long> {
    Album save(Album album);

    Optional<Album> findById(Long id);

    @Query("""
            select a from Album a 
            join fetch a.songs songs 
            join fetch a.artists artists
            where a.id = :id""")
    Optional<Album> findByIdAndSongsAndArtists(Long id);
}