package org.sid.movieservice.controllers;

import org.sid.movieservice.dto.MovieRequest;
import org.sid.movieservice.dto.MovieResponse;
import org.sid.movieservice.services.MovieService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieResponse> getAll() {
        return movieService.getAll();
    }

    @GetMapping("/{director}")
    public List<MovieResponse> getByDirector(@PathVariable("director") String director) {
        return movieService.getByDirector(director);
    }

    @PostMapping
    public MovieResponse addMovie(@RequestBody MovieRequest movieRequest) {
        return movieService.add(movieRequest);
    }

    @PutMapping("/update/{id}")
    public MovieResponse updateMovie(@PathVariable("id")Long id, @RequestBody MovieRequest movieRequest) {
        return movieService.update(id, movieRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMovie(@PathVariable("id")Long id) {
        movieService.delete(id);
    }
}
