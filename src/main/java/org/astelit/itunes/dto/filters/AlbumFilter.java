package org.astelit.itunes.dto.filters;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.enums.Genre;

@Getter
@Setter
@NoArgsConstructor
public class AlbumFilter extends SearchRequest {

    private Long singer;
}
