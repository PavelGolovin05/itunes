package org.astelit.itunes.repository;

import org.astelit.itunes.dto.filters.PlaylistFilter;
import org.astelit.itunes.entity.Playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long>, JpaSpecificationExecutor<Playlist> {

    default Page<Playlist> search(PlaylistFilter request) {
        return findAll((Specification<Playlist>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getAuthor() != null) {
                predicates.add(cb.equal(root.get("author").get("id"), request.getAuthor()));
            }

            if (request.getSong() != null) {
                predicates.add(cb.isMember(request.getSong(), root.get("song").get("id")));
            }

            predicates.add(cb.like(cb.upper(root.get("name")), request.getLikeString()));
            query.orderBy(cb.desc(root.get("updatedAt")));

            return cb.and(predicates.toArray(new Predicate[0]));
        }, request.pageable());
    }
}
