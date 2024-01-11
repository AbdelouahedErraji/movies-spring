package org.sid.movieservice.repository;

import org.sid.movieservice.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByDirectorIgnoreCase(String director);
}
