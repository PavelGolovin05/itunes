package org.astelit.itunes.repository;

import org.astelit.itunes.dto.filters.SongFilter;
import org.astelit.itunes.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long>, JpaSpecificationExecutor<Song> {
    default Page<Song> search(SongFilter request) {
        return findAll((Specification<Song>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getGenre() != null) {
                predicates.add(cb.equal(root.get("album").get("genre"), request.getGenre()));
            }

            if(request.getAlbum() != null) {
                predicates.add(cb.equal(root.get("album").get("id"), request.getAlbum()));
            }
            predicates.add(cb.like(cb.upper(root.get("name")), request.getLikeString()));
            query.orderBy(cb.desc(root.get("updatedAt")));

            return cb.and(predicates.toArray(new Predicate[0]));
        }, request.pageable());
    }
}
