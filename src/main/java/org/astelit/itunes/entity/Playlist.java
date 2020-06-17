package org.astelit.itunes.entity;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.contstraint.PlaylistName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "playlists")
@Getter
@Setter
public class Playlist extends BaseEntity {
    @PlaylistName
    private String name;

    @NotNull
    @JoinColumn(name = "id_author")
    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    @ManyToMany
    @JoinTable (name="song_playlist",
            joinColumns=@JoinColumn (name="id_playlist"),
            inverseJoinColumns=@JoinColumn(name="id_song"))
    private Set<Song> songs = new HashSet<>();;
}
