package com.kaanaydemir.movie.mapper;

import com.kaanaydemir.movie.dto.MovieDto;
import com.kaanaydemir.movie.schemas.Movie;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    Movie movieDtoToMovie(MovieDto movieDto);

    MovieDto movieToMovieDto(Movie movie);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Movie updateMovieFromMovieDto(MovieDto movieDto, @MappingTarget Movie movie);


}
