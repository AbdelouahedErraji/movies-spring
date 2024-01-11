package org.sid.movieservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieResponse {
    private Long id;
    private String title;
    private int seats;
    private double price;
    private String director;
}
