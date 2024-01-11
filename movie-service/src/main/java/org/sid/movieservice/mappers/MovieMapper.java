package org.sid.movieservice.mappers;

import org.sid.movieservice.dto.MovieRequest;
import org.sid.movieservice.dto.MovieResponse;
import org.sid.movieservice.models.Movie;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MovieMapper {
    public MovieResponse movieToResponse(Movie movie) {
        MovieResponse movieResponse = new MovieResponse();
        BeanUtils.copyProperties(movie, movieResponse);
        return movieResponse;
    }

    public Movie requestToMovie(MovieRequest movieRequest) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieRequest, movie);
        return movie;
    }
}
