package org.sid.directorservice.mappers;

import org.sid.directorservice.dto.DirectorRequest;
import org.sid.directorservice.dto.DirectorResponse;
import org.sid.directorservice.models.Director;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DirectorMapper {
    public DirectorResponse directorToResponse(Director director) {
        DirectorResponse directorResponse = new DirectorResponse();
        BeanUtils.copyProperties(director, directorResponse);
        return directorResponse;
    }

    public Director requestToDirector(DirectorRequest directorRequest) {
        Director director = new Director();
        BeanUtils.copyProperties(directorRequest, director);
        return director;
    }
}
