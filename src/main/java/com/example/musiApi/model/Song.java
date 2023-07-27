package com.example.musiApi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,scope=Song.class,property="songId")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long songId;
    @Column(unique = true)
    private String songName;
    private String songSinger;
    private String songType;

    @ManyToMany(mappedBy = "songs",cascade = CascadeType.ALL)
    private List<User> users ;
}
