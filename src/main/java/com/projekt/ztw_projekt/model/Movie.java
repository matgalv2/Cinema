package com.projekt.ztw_projekt.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "movie_id")
//    private Set<Assignment> assignments;

    public void update(final Movie movie) {
        name = movie.name;
        duration = movie.duration;
        description = movie.description;
    }
}