package org.sid.movieservice.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MovieRequest {
    private String title;
    private Integer seats;
    private Double price;
    private String director;
}
