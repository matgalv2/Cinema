package com.projekt.ztw_projekt.controller;


import com.projekt.ztw_projekt.dto.MovieResponse;
import com.projekt.ztw_projekt.model.Movie;
import com.projekt.ztw_projekt.repositories.AssignmentRepository;
import com.projekt.ztw_projekt.repositories.MovieRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@RestController
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    private final MovieRepository movieRepository;
    private final AssignmentRepository assignmentRepository;

    @GetMapping(value="/movies")
    public ResponseEntity<?> readAllMovies() {

        List<Movie> movies = movieRepository.findAll();
        List<MovieResponse> movieResponses = new ArrayList<>();

        for (Movie movie: movies) {
            MovieResponse movieResponse = Movie.getAllInfo(movieRepository, assignmentRepository, movie.getId());
            if(movieResponse != null)
                movieResponses.add(movieResponse);
        }
        return ResponseEntity.ok(movieResponses);
    }

    @GetMapping(value = "/movies/{id}")
    public ResponseEntity<?> readMovieById(@PathVariable int id) {

        if(!movieRepository.existsById(id))
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(Movie.getAllInfo(movieRepository, assignmentRepository, id));
    }

    @PostMapping("/movies")
    public ResponseEntity<?> createMovie(@RequestBody @Valid Movie newMovie){
        Movie addedMovie = movieRepository.save(newMovie);
        return ResponseEntity.created(URI.create("/" + addedMovie.getId())).body(addedMovie);
    }

    @PutMapping(value = "/movies/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable int id, @RequestBody @Valid Movie updatedMovie) {

        if(!movieRepository.existsById(id))
            return ResponseEntity.notFound().build();


        movieRepository.findById(id)
                .ifPresent(movie -> {
                    movie.update(updatedMovie);
                    movieRepository.save(movie);
                });
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/movies/{id}")
    public ResponseEntity<?> removeMovie(@PathVariable int id) {
        if(!movieRepository.existsById(id))
            return ResponseEntity.notFound().build();

        movieRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
