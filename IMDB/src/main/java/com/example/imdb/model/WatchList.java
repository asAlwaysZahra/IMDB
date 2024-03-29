package com.example.imdb.model;

import com.example.imdb.model.responses.WatchListResponse;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Getter
@Setter
public class WatchList {

    @Id
    @GeneratedValue
    private Integer id;

    private int size;

    @ManyToMany
    @JoinTable(
            name = "watchlist_movies",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "titleId")
    )
    private Set<Movie> movies;

    public WatchListResponse response() {
        return WatchListResponse.builder()
                .size(size)
                .movies(movies.stream().map(Movie::informationResponse).toList())
                .build();
    }
}
