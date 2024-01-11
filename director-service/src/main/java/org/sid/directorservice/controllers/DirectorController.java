package org.sid.directorservice.controllers;

import org.sid.directorservice.dto.DirectorRequest;
import org.sid.directorservice.dto.DirectorResponse;
import org.sid.directorservice.dto.DirectorResponseWithMovies;
import org.sid.directorservice.services.DirectorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/directors")
public class DirectorController {
    private final DirectorService directorService;
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @PostMapping
    public DirectorResponse add(@RequestBody DirectorRequest directorRequest) {
        return directorService.add(directorRequest);
    }

    @GetMapping
    public List<DirectorResponse> getAll() {
        return directorService.getAll();
    }

    @GetMapping("/movies/{director_id}")
    public DirectorResponseWithMovies getDirectorWithMovies(@PathVariable("director_id") Long director_id) {
        return directorService.getDirectorWithMovies(director_id);
    }

    @GetMapping("/movies")
    public List<DirectorResponseWithMovies> getAllWithMovies() {
        return directorService.getAllWithMovies();
    }

    @PutMapping("/update/{id}")
    public DirectorResponse update(@PathVariable("id")Long id, @RequestBody DirectorRequest directorRequest) {
        return directorService.update(id, directorRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id")Long id) {
        directorService.delete(id);
    }
}
