package org.sid.directorservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Movie {
    private String title;
    private int seats;
    private double price;
}
