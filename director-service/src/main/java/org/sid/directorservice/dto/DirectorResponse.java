package org.sid.directorservice.dto;

import lombok.Data;
import org.sid.directorservice.models.Movie;

import java.util.List;

@Data
public class DirectorResponse {
    private Long id;
    private String name;
}
