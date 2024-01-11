package org.sid.directorservice.dto;

import lombok.Data;
import org.sid.directorservice.models.Movie;

import java.util.List;

@Data
public class DirectorResponseWithMovies {
    private String name;
    private List<Movie> movies;
}
