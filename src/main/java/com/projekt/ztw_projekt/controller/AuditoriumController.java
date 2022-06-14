package com.projekt.ztw_projekt.controller;


import com.projekt.ztw_projekt.model.Auditorium;
import com.projekt.ztw_projekt.model.Movie;
import com.projekt.ztw_projekt.repositories.AuditoriumRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@RestController
public class AuditoriumController {
    private static final Logger logger = LoggerFactory.getLogger(AuditoriumController.class);
    private final AuditoriumRepository auditoriumRepository;

    @GetMapping(value="/auditoriums")
    public ResponseEntity<?> readAllAuditoriums() {
        return ResponseEntity.ok(auditoriumRepository.findAll());
    }

    @GetMapping(value = "/auditoriums/{id}")
    public ResponseEntity<?> readAuditoriumById(@PathVariable int id) {
        return auditoriumRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/auditoriums")
    public ResponseEntity<?> createAuditorium(@RequestBody @Valid Auditorium newAuditorium){
        Auditorium addedAuditorium = auditoriumRepository.save(newAuditorium);
        return ResponseEntity.created(URI.create("/" + addedAuditorium.getId())).body(addedAuditorium);
    }

    @PutMapping(value = "/auditoriums/{id}")
    public ResponseEntity<?> updateAuditorium(@PathVariable int id, @RequestBody @Valid Auditorium updatedAuditorium) {
        if(!auditoriumRepository.existsById(id))
            return ResponseEntity.notFound().build();

        auditoriumRepository.findById(id)
                .ifPresent(auditorium -> {
                    auditorium.update(updatedAuditorium);
                    auditoriumRepository.save(auditorium);
                });
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping(value = "/auditoriums/{id}")
    public ResponseEntity<?> removeAuditorium(@PathVariable int id) {
        if(!auditoriumRepository.existsById(id))
            return ResponseEntity.notFound().build();

        auditoriumRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
