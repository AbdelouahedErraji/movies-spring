package org.sid.movieservice.services;

import jakarta.persistence.EntityNotFoundException;
import org.sid.movieservice.dto.MovieRequest;
import org.sid.movieservice.dto.MovieResponse;
import org.sid.movieservice.mappers.MovieMapper;
import org.sid.movieservice.models.Movie;
import org.sid.movieservice.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    public MovieResponse add(MovieRequest movieRequest) {
        Movie movie = movieMapper.requestToMovie(movieRequest);
        movieRepository.save(movie);
        return movieMapper.movieToResponse(movie);
    }

    public List<MovieResponse> getByDirector(String director) {
        List<Movie> movies = movieRepository.findByDirectorIgnoreCase(director);
        return movies.stream().map(movieMapper::movieToResponse).toList();
    }

    public MovieResponse getMovie(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if(movieOptional.isPresent()) {
            return movieMapper.movieToResponse(movieOptional.get());
        } else {
            throw new EntityNotFoundException("Movie with id="+id+" not found");
        }
    }

    public List<MovieResponse> getAll() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map(movieMapper::movieToResponse).toList();
    }

    public MovieResponse update(Long id, MovieRequest movieRequest) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        Movie movie;
        if(movieOptional.isPresent()) {
            movie = movieOptional.get();
            movie.setSeats(movieRequest.getSeats() != null ? movieRequest.getSeats() : movie.getSeats());
            movie.setPrice(movieRequest.getPrice() != null ? movieRequest.getPrice() : movie.getPrice());
            movie.setTitle(movieRequest.getTitle() != null ? movieRequest.getTitle() : movie.getTitle());
            movie.setDirector(movieRequest.getDirector() != null ? movieRequest.getDirector() : movie.getDirector());

            movieRepository.save(movie);
            return movieMapper.movieToResponse(movie);
        } else {
            throw new EntityNotFoundException("Movie with id="+id+" not found");
        }
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }
}
