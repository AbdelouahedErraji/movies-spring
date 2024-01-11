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
    public MovieResponse addMovie(@RequestParam("title") String title,
                                  @RequestParam("director") String director,
                                  @RequestParam("price") double price,
                                  @RequestParam("seats") int seats,
                                  @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        MovieRequest movieRequest = new MovieRequest();
        movieRequest.setDirector(director);
        movieRequest.setSeats(seats);
        movieRequest.setPrice(price);
        movieRequest.setTitle(title);
        return movieService.add(movieRequest, image);
    }

    @PutMapping("/update/{id}")
    public MovieResponse updateMovie(@PathVariable("id")Long id, @RequestParam(value = "title", required = false) String title,
                                     @RequestParam(value = "director", required = false) String director,
                                     @RequestParam(value = "price", required = false) Double price,
                                     @RequestParam(value = "seats", required = false) Integer seats,
                                     @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        MovieRequest movieRequest = new MovieRequest();
        movieRequest.setTitle(title);
        movieRequest.setPrice(price);
        movieRequest.setSeats(seats);
        movieRequest.setDirector(director);
        return movieService.update(id, movieRequest, image);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMovie(@PathVariable("id")Long id) {
        movieService.delete(id);
    }
}
