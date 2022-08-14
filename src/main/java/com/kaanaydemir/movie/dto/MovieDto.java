package com.kaanaydemir.movie.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class MovieDto  implements Serializable {

    private String title;
    private Float rating;
    private LocalDate releaseDate;
    private List<ActorDto> cast;
}
