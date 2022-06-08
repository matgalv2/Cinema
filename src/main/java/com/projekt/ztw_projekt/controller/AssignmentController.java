package com.projekt.ztw_projekt.controller;

import com.projekt.ztw_projekt.dto.AssignmentRequest;
import com.projekt.ztw_projekt.model.Assignment;
import com.projekt.ztw_projekt.model.Auditorium;
import com.projekt.ztw_projekt.model.Movie;
import com.projekt.ztw_projekt.repositories.AssignmentRepository;
import com.projekt.ztw_projekt.repositories.AuditoriumRepository;
import com.projekt.ztw_projekt.repositories.MovieRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public List<Assignment> readAllAssignments() {
        return assignmentRepository.findAll();
    }

    @GetMapping(value = "/assignments/{id}")
    public Assignment readAssignmentById(@RequestParam int id) {
        return assignmentRepository.getById(id);
    }

    @GetMapping(value = "/assignments/movie-{id}")
    public List<Assignment> readAllAssignmentsByMovieId(@PathVariable int id){;
        return assignmentRepository.findAll().stream().filter(ass -> ass.getMovie().getId() == id).toList();
    }



    @PostMapping("/assignments")
    public Assignment createAssignment(@RequestBody AssignmentRequest request) {
        Movie movie = movieRepository.getById(request.getMovieId());
        Auditorium auditorium = auditoriumRepository.getById(request.getAuditoriumId());
        Assignment assignment = new Assignment();
        assignment.setStartsAt(request.getStartsAt());
        assignment.setStartDate(request.getStartDate());
        assignment.setEndDate(request.getEndDate());
        assignment.setAuditorium(auditorium);
        assignment.setMovie(movie);
        return assignmentRepository.save(assignment);
    }

    @PutMapping(value = "/assignments/{id}")
    public Assignment updateAssignment(@RequestBody @Valid Assignment updatedAssignment) {
        Assignment assignment = assignmentRepository.getById(updatedAssignment.getId());
        assignment.update(updatedAssignment);
        return assignmentRepository.save(assignment);
    }

    @Transactional
    @DeleteMapping(value = "/assignments/{id}")
    public void removeAssignment(@PathVariable int id) {
        assignmentRepository.findById(id).ifPresent(assignmentRepository::delete);
//        assignmentRepository.deleteById(id);
    }


}
