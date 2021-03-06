package org.astelit.itunes.repository;

import org.astelit.itunes.dto.filters.AlbumFilter;
import org.astelit.itunes.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long>, JpaSpecificationExecutor<Album> {
    default Page<Album> search(AlbumFilter request) {
        return findAll((Specification<Album>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getSinger() != null) {
                predicates.add(cb.equal(root.get("singer").get("id"), request.getSinger()));
            }

            predicates.add(cb.like(cb.upper(root.get("name")), request.getLikeString()));
            query.orderBy(cb.desc(root.get("updatedAt")));

            return cb.and(predicates.toArray(new Predicate[0]));
        }, request.pageable());
    }
}
