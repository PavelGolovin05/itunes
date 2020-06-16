package org.astelit.itunes.dto.album;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.contstraint.Cyrillic;
import org.astelit.itunes.dto.EntityResponse;
import org.astelit.itunes.entity.Album;
import org.astelit.itunes.entity.Singer;
import org.astelit.itunes.enums.Genre;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class AlbumResponse extends EntityResponse {

    @Cyrillic
    private final String name;

    @Enumerated(EnumType.STRING)
    private final Genre genre;

    private final long date_realize;

    private final Long singer;

    public AlbumResponse(Album album) {
        super(album);
        this.name = album.getName();
        this.genre = album.getGenre();
        this.date_realize = album.getDate_realize();
        this.singer = album.getSinger().getId();
    }
}
