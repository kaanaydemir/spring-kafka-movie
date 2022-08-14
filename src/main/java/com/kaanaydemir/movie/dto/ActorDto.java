package com.kaanaydemir.movie.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ActorDto implements Serializable {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

}
