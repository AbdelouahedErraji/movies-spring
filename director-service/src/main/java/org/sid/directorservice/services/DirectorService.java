package org.sid.directorservice.services;

import jakarta.persistence.EntityNotFoundException;
import org.sid.directorservice.dto.DirectorRequest;
import org.sid.directorservice.dto.DirectorResponse;
import org.sid.directorservice.dto.DirectorResponseWithMovies;
import org.sid.directorservice.mappers.DirectorMapper;
import org.sid.directorservice.models.Director;
import org.sid.directorservice.models.Movie;
import org.sid.directorservice.repositories.DirectorRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {
    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;
    private final WebClient webClient;

    public DirectorService(DirectorRepository directorRepository, DirectorMapper directorMapper, WebClient webClient) {
        this.directorRepository = directorRepository;
        this.directorMapper = directorMapper;
        this.webClient = webClient;
    }

    public DirectorResponse add(DirectorRequest directorRequest) {
        Director director = directorMapper.requestToDirector(directorRequest);
        directorRepository.save(director);
        return directorMapper.directorToResponse(director);
    }

    public DirectorResponseWithMovies getDirectorWithMovies(Long id) {
        Optional<Director> directorOptional = directorRepository.findById(id);
        if(directorOptional.isPresent()) {
            Director director = directorOptional.get();
            return directorMoviesList(director);
        } else {
            throw new EntityNotFoundException("Director not found");
        }
    }

    public List<DirectorResponseWithMovies> getAllWithMovies() {
        List<Director> directors = directorRepository.findAll();
        List<DirectorResponseWithMovies> responses = new ArrayList<>();
        directors.forEach(director -> {
            responses.add(directorMoviesList(director));
        });
        return responses;
    }

    public List<DirectorResponse> getAll() {
        List<Director> directors = directorRepository.findAll();
        return directors.stream().map(directorMapper::directorToResponse).toList();
    }

    public DirectorResponse update(Long id, DirectorRequest directorRequest) {
        Optional<Director> directorOptional = directorRepository.findById(id);
        if(directorOptional.isPresent()) {
            Director director = directorOptional.get();
            director.setName(directorRequest.getName() != null ? directorRequest.getName() : director.getName());
            directorRepository.save(director);
            return directorMapper.directorToResponse(director);
        } else {
            throw new EntityNotFoundException("Director not found");
        }
    }

    public void delete(Long id) {
        directorRepository.deleteById(id);
    }

    private DirectorResponseWithMovies directorMoviesList(Director director) {
        Movie[] movies = webClient.get()
                .uri("http://localhost:8080/api/movies/" + director.getName())
                .retrieve()
                .bodyToMono(Movie[].class)
                .block();
        DirectorResponseWithMovies directorResponse = new DirectorResponseWithMovies();
        assert movies != null;
        directorResponse.setId(director.getId());
        directorResponse.setMovies(Arrays.stream(movies).toList());
        directorResponse.setName(director.getName());
        return directorResponse;
    }
}
