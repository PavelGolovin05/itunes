package org.astelit.itunes.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraint.Cyrillic;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "singers")
@Entity
@NoArgsConstructor
public class Singer extends BaseEntity{

    @Cyrillic
    private String name;

    @OneToMany(mappedBy = "singer", fetch = FetchType.LAZY)
    private Set<Album> albums = new HashSet<>();
}
