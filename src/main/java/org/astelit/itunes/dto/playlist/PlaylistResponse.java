package org.astelit.itunes.dto.playlist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.dto.EntityResponse;
import org.astelit.itunes.dto.song.SongResponse;
import org.astelit.itunes.entity.Playlist;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class PlaylistResponse extends EntityResponse {
    private final String name;
    private final Set<SongResponse> songs;

    public PlaylistResponse(Playlist playlist) {
        super(playlist);
        this.name = playlist.getName();
        this.songs = playlist.getSongs().stream().map(SongResponse::new).collect(Collectors.toSet());
    }
}
