package com.projekt.ztw_projekt.controller;


import com.projekt.ztw_projekt.model.Auditorium;
import com.projekt.ztw_projekt.repositories.AuditoriumRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
@RestController
public class AuditoriumController {
    private static final Logger logger = LoggerFactory.getLogger(AuditoriumController.class);
    private final AuditoriumRepository auditoriumRepository;

    @GetMapping(value="/auditoriums")
    public List<Auditorium> readAllAuditoriums() {
        return auditoriumRepository.findAll();
    }

    @GetMapping(value = "/auditoriums/{id}")
    public Auditorium readAuditoriumById(@PathVariable int id) {
        return auditoriumRepository.getById(id);
    }

    @PostMapping("/auditoriums")
    public Auditorium createAuditorium(@RequestBody @Valid Auditorium newAuditorium){
        return auditoriumRepository.save(newAuditorium);
    }

    @PutMapping(value = "/auditoriums/{id}")
    public Auditorium updateAuditorium(@PathVariable int id, @RequestBody @Valid Auditorium updatedAuditorium) {
        Auditorium auditorium = auditoriumRepository.getById(id);
        auditorium.update(updatedAuditorium);
        return auditoriumRepository.save(auditorium);
    }

    @DeleteMapping(value = "/auditoriums/{id}")
    public void removeAuditorium(@PathVariable int id) {
        auditoriumRepository.deleteById(id);
    }
}
