package org.astelit.itunes.dto.playlist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraint.PlaylistName;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PlaylistCreateRequest {
    @PlaylistName
    private String name;

    @NotNull
    private Long author;

    private Set<Long> songs = new HashSet<>();
}
