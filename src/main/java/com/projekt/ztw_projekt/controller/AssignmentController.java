package com.projekt.ztw_projekt.controller;

import com.projekt.ztw_projekt.dto.AssignmentRequest;
import com.projekt.ztw_projekt.model.Assignment;
import com.projekt.ztw_projekt.model.Auditorium;
import com.projekt.ztw_projekt.model.Movie;
import com.projekt.ztw_projekt.repositories.AssignmentRepository;
import com.projekt.ztw_projekt.repositories.AuditoriumRepository;
import com.projekt.ztw_projekt.repositories.MovieRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} )
@AllArgsConstructor
@RestController
public class AssignmentController {
    private static final Logger logger = LoggerFactory.getLogger(AssignmentController.class);
    private final AssignmentRepository assignmentRepository;
    private final MovieRepository movieRepository;
    private final AuditoriumRepository auditoriumRepository;

    @GetMapping(value="/assignments")
    public ResponseEntity<?> readAllAssignments() {
        return ResponseEntity.ok(assignmentRepository.findAll());
    }

    @GetMapping(value = "/assignments/{id}")
    public ResponseEntity<?> readAssignmentById(@PathVariable int id) {
        return assignmentRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/assignments/movieID={id}")
    public List<Assignment> readAllAssignmentsByMovieId(@PathVariable int id){
        return assignmentRepository.findAll().stream()
                .filter(assignment -> assignment.getMovie().getId() == id)
                .toList();
    }



    @PostMapping("/assignments")
    public ResponseEntity<?> createAssignment(@RequestBody AssignmentRequest request) {

        if(!movieRepository.existsById(request.getMovieId()) || !auditoriumRepository.existsById(request.getAuditoriumId()) ||
                assignmentRepository.existsByAuditoriumIdAndStartsAt(request.getAuditoriumId(), request.getStartsAt()) ||
                assignmentRepository.existsByMovieIdAndStartsAt(request.getMovieId(), request.getStartsAt()))
            return ResponseEntity.badRequest().build();


        Movie movie = movieRepository.getById(request.getMovieId());
        Auditorium auditorium = auditoriumRepository.getById(request.getAuditoriumId());
        Assignment assignment = new Assignment();
        assignment.setStartsAt(request.getStartsAt());
        assignment.setStartDate(request.getStartDate());
        assignment.setEndDate(request.getEndDate());
        assignment.setAuditorium(auditorium);
        assignment.setMovie(movie);
        Assignment added = assignmentRepository.save(assignment);

        return ResponseEntity.created(URI.create("/" + added.getId())).body(added);
    }

    @PutMapping(value = "/assignments/{id}")
    public ResponseEntity<?> updateAssignment(@PathVariable int id, @RequestBody @Valid Assignment updatedAssignment) {
        if(!assignmentRepository.existsById(id))
            return ResponseEntity.notFound().build();

        Assignment assignment = assignmentRepository.getById(updatedAssignment.getId());
        assignment.update(updatedAssignment);
        return ResponseEntity.ok().body(assignmentRepository.save(assignment));
    }

    @Transactional
    @DeleteMapping(value = "/assignments/{id}")
    public ResponseEntity<?> removeAssignment(@PathVariable int id) {
        if(!assignmentRepository.existsById(id))
            return ResponseEntity.notFound().build();

        assignmentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
