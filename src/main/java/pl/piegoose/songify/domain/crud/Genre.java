package pl.piegoose.songify.domain.crud.genre;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.piegoose.songify.domain.crud.util.BaseEntity;

@Entity
@NoArgsConstructor
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
class Genre extends BaseEntity {

    @Id
    @GeneratedValue(generator = "genre_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "genre_id_seq",
            sequenceName = "genre_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String name;

}
