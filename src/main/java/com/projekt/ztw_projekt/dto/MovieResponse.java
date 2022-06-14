package com.projekt.ztw_projekt.dto;

import com.projekt.ztw_projekt.model.Movie;
import lombok.Data;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Data
public class MovieResponse {
    private String name;
    private int duration;
    private String description;
    private String imageLink;
    private List<Integer> auditoriums;
    private List<Time> times;

    private MovieResponse(){}

    public static MovieResponse MovieResponseByMovie(Movie movie){
        MovieResponse movieResponse = new MovieResponse();

        movieResponse.name = movie.getName();
        movieResponse.duration = movie.getDuration();
        movieResponse.description = movie.getDescription();
        movieResponse.imageLink = movie.getImageLink();

        movieResponse.auditoriums = new ArrayList<>();
        movieResponse.times = new ArrayList<>();
        return movieResponse;
    }
}
