package org.astelit.itunes.dto.song;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.dto.EntityResponse;
import org.astelit.itunes.entity.Song;

@Getter
@Setter
public class SongResponse extends EntityResponse {
    private final String name;

    private final Long duration;

    private final Long album;

    public SongResponse(Song song) {
        super(song);
        this.name = song.getName();
        this.duration = song.getDuration();
        this.album = song.getAlbum().getId();
    }
}
