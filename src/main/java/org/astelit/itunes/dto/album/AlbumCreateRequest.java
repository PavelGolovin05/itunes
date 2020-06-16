package org.astelit.itunes.dto.album;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraint.Cyrillic;
import org.astelit.itunes.enums.Genre;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AlbumCreateRequest {

    @Cyrillic
    private String name;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private Long date_realize;

    @NotNull
    private Long singer;
}
