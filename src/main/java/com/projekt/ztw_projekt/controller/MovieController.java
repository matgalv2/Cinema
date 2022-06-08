package com.projekt.ztw_projekt.controller;


import com.projekt.ztw_projekt.model.Movie;
import com.projekt.ztw_projekt.repositories.MovieRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@RestController
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    private final MovieRepository movieRepository;

    @GetMapping(value="/movies")
    public List<Movie> readAllMovies() {
        return movieRepository.findAll();
    }

    @GetMapping(value = "/movies/{id}")
    public Movie readMovieById(@PathVariable int id) {
        return movieRepository.getById(id);
    }

    @PostMapping("/movies")
    public Movie createMovie(@RequestBody @Valid Movie newMovie){
        return movieRepository.save(newMovie);
    }

    @PutMapping(value = "/movies/{id}")
    public Movie updateMovie(@PathVariable int id, @RequestBody @Valid Movie updatedMovie) {
        Movie movie = movieRepository.getById(id);
        movie.update(updatedMovie);
        return movieRepository.save(movie);
    }

    @DeleteMapping(value = "/movies/{id}")
    public void removeMovie(@PathVariable int id) {
        movieRepository.deleteById(id);
    }
}
