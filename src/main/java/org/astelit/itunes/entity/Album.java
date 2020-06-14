package org.astelit.itunes.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraint.Cyrillic;
import org.astelit.itunes.enums.Genre;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "albums")
@Entity
@NoArgsConstructor
public class Album extends BaseEntity{

    @Cyrillic
    private String name;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY)
    private Set<Song> songs = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_singer")
    private Singer singer;
}
