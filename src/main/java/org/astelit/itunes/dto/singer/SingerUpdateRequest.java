package org.astelit.itunes.dto.singer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class SingerUpdateRequest {
    @NotNull
    private Long id;

    private String name;
}
