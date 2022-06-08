package com.projekt.ztw_projekt.controller;


import com.projekt.ztw_projekt.model.BookedTicket;
import com.projekt.ztw_projekt.repositories.BookedTicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BookedTicketController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    private final BookedTicketRepository bookedTicketRepository;

    public BookedTicketController(BookedTicketRepository repository) {
        this.bookedTicketRepository = repository;
    }

    @GetMapping(value="/booked-tickets")
    public List<BookedTicket> readAllBookedTickets() {
        return bookedTicketRepository.findAll();
    }

    @GetMapping(value = "/booked-tickets/{id}")
    public BookedTicket readBookedTicketById(@PathVariable int id) {
        return bookedTicketRepository.getById(id);
    }

    @PostMapping("/booked-tickets")
    public BookedTicket createMovie(@RequestBody @Valid BookedTicket newBookedTicket){
       return bookedTicketRepository.save(newBookedTicket);
    }

    @PutMapping(value = "/booked-tickets/{id}")
    public BookedTicket updateMovie(@PathVariable int id, @RequestBody @Valid BookedTicket updatedBookedTicket) {
        BookedTicket bookedTicket = bookedTicketRepository.getById(id);
        bookedTicket.update(updatedBookedTicket);
        return bookedTicketRepository.save(bookedTicket);
    }

    @DeleteMapping(value = "/booked-tickets/{id}")
    public void removeBookedTicket(@PathVariable int id) {
        bookedTicketRepository.deleteById(id);
    }


}
