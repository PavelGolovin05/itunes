package org.astelit.itunes.repository;

import org.astelit.itunes.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
