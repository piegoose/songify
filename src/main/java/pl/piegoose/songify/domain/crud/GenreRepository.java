package pl.piegoose.songify.domain.crud;

import org.springframework.data.repository.Repository;

interface GenreRepository extends Repository<Genre, Long> {

    Genre save(Genre genre);
}
