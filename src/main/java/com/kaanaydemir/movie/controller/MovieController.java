package com.kaanaydemir.movie.controller;

import com.kaanaydemir.movie.dto.MovieDto;
import com.kaanaydemir.movie.kafka.producer.KafkaProducer;
import com.kaanaydemir.movie.mapper.MovieMapper;
import com.kaanaydemir.movie.schemas.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/movie")
public class MovieController {

    private final MovieMapper movieMapper;
    private final KafkaProducer kafkaProducer;

    public MovieController(MovieMapper movieMapper, KafkaProducer kafkaProducer) {
        this.movieMapper = movieMapper;
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping(path = "/save")
    public ResponseEntity saveMovie(@RequestBody MovieDto movieDto) {
        Movie movie = movieMapper.movieDtoToMovie (movieDto);
        kafkaProducer.sendMessage ("movies.topic",movie);
        return ResponseEntity.ok ().build ();
    }
}
