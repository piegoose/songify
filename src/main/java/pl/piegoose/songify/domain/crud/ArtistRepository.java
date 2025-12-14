package pl.piegoose.songify.domain.crud;

import org.springframework.data.repository.Repository;

import java.util.Set;

interface ArtistRepository extends Repository<Artist, Long> {

    Artist save(Artist artist);

    Set<Artist> findAll();
}
