package org.astelit.itunes.dto.singer;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.dto.EntityResponse;
import org.astelit.itunes.entity.Singer;

@Getter
@Setter
public class SingerResponse  extends EntityResponse {
    private final String name;

    public SingerResponse(Singer singer) {
        super(singer);
        this.name = singer.getName();
    }
}
