package com.projekt.ztw_projekt.controller;


import com.projekt.ztw_projekt.dto.BookedTicketRequest;
import com.projekt.ztw_projekt.model.BookedTicket;
import com.projekt.ztw_projekt.repositories.AssignmentRepository;
import com.projekt.ztw_projekt.repositories.BookedTicketRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BookedTicketController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    private final BookedTicketRepository bookedTicketRepository;
    private final AssignmentRepository assignmentRepository;



    @GetMapping(value="/booked-tickets")
    public ResponseEntity<?> readAllBookedTickets() {
        return ResponseEntity.ok(bookedTicketRepository.findAll());
    }

    @GetMapping(value = "/booked-tickets/{id}")
    public ResponseEntity<?> readBookedTicketById(@PathVariable int id) {
        if(!bookedTicketRepository.existsById(id))
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(bookedTicketRepository.findById(id));
    }

    @GetMapping(value="/booked-tickets/code={code}")
    public ResponseEntity<?> readAllBookedTicketsByCode(@PathVariable String code) {
        return ResponseEntity.ok(bookedTicketRepository.findAllByCode(code));
    }

    @GetMapping(value = "/booked-tickets/assignmentID={id}")
    public ResponseEntity<?> readBookedTicketsByAssignment(@PathVariable int id){
        return ResponseEntity.ok( bookedTicketRepository
                .findAllByAssignmentId(id)
                .stream()
                .map(BookedTicket::getSeatNumber)
                .toList());
    }

    @GetMapping(value = "/booked-tickets/assignmentID={id}/movieDate={date}")
    public ResponseEntity<?> readBookedTicketsByAssignmentAndDate(@PathVariable int id, @PathVariable Date date){
        return ResponseEntity.ok(bookedTicketRepository
                .findAllByAssignmentIdAndMovieDate(id, date)
                .stream()
                .map(BookedTicket::getSeatNumber)
                .toList());
    }

    @PostMapping("/booked-tickets")
    public ResponseEntity<?> createMovie(@RequestBody @Valid BookedTicketRequest request){
       if(!assignmentRepository.existsByMovieIdAndStartsAt(request.getMovieId(), request.getStartsAt()))
            return ResponseEntity.badRequest().build();
       else{
           BookedTicket bookedTicket = new BookedTicket();
           bookedTicket.updateWithRequest(request, assignmentRepository.findByMovieIdAndStartsAt(request.getMovieId(), request.getStartsAt()));
           return ResponseEntity.created(URI.create("/" + bookedTicketRepository.save(bookedTicket).getId())).body(bookedTicket);
       }
    }

//    @PutMapping(value = "/booked-tickets/{id}")
//    public BookedTicket updateMovie(@PathVariable int id, @RequestBody @Valid BookedTicket updatedBookedTicket) {
//        BookedTicket bookedTicket = bookedTicketRepository.getById(id);
//        bookedTicket.update(updatedBookedTicket);
//        return bookedTicketRepository.save(bookedTicket);
//    }
//
//    @DeleteMapping(value = "/booked-tickets/{id}")
//    public void removeBookedTicket(@PathVariable int id) {
//        bookedTicketRepository.deleteById(id);
//    }

}
