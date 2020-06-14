package org.astelit.itunes.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraint.Cyrillic;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "songs")
@Entity
@NoArgsConstructor
public class Song extends BaseEntity{

    @Cyrillic
    private String name;

    private long duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_album")
    private Album album;
}
