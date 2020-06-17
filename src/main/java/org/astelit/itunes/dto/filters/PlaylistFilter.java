package org.astelit.itunes.dto.filters;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.dto.SearchRequest;

@Getter
@Setter
@NoArgsConstructor
public class PlaylistFilter extends SearchRequest {
    private Long author;
    private Long song;
}
