package org.sid.movieservice.mappers;

import org.sid.movieservice.dto.MovieRequest;
import org.sid.movieservice.dto.MovieResponse;
import org.sid.movieservice.models.Movie;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MovieMapper {
    public MovieResponse movieToResponse(Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .price(movie.getPrice())
                .seats(movie.getSeats())
                .director(movie.getDirector()).build();
    }

    public Movie requestToMovie(MovieRequest movieRequest) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieRequest, movie);
        return movie;
    }
}
