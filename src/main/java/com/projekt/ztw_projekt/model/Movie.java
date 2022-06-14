package com.projekt.ztw_projekt.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projekt.ztw_projekt.dto.MovieResponse;
import com.projekt.ztw_projekt.repositories.AssignmentRepository;
import com.projekt.ztw_projekt.repositories.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Movie's name must not be null.")
    private String name;
    @NotNull(message = "Movie's duration must not be null.")
    private int duration;
    @NotBlank(message = "Movie's description must not be null.")
    private String description;

    @Column(name = "image_link")
    private String imageLink;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "movie_id")
//    private Set<Assignment> assignments;

    public void update(final Movie movie) {
        name = movie.name;
        duration = movie.duration;
        description = movie.description;
        imageLink = movie.imageLink;
    }
    public static MovieResponse getAllInfo(MovieRepository movieRepository, AssignmentRepository assignmentRepository, int movieID){
        if(!movieRepository.existsById(movieID))
            return null;

        List<Assignment> assignments = assignmentRepository.findByMovieId(movieID);
        MovieResponse movieResponse = MovieResponse.MovieResponseByMovie(movieRepository.getById(movieID));
        for (Assignment assignment : assignments) {
            movieResponse.getAuditoriums().add(assignment.getAuditorium().getId());
            movieResponse.getTimes().add(assignment.getStartsAt());
        }
        return movieResponse;
    }
}