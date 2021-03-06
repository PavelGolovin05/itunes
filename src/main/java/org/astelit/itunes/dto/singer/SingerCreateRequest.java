package org.astelit.itunes.dto.singer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraint.Cyrillic;

@Getter
@Setter
@NoArgsConstructor
public class SingerCreateRequest {

    @Cyrillic
    private String name;
}
