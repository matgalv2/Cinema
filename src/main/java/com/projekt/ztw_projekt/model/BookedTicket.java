package com.projekt.ztw_projekt.model;


import com.projekt.ztw_projekt.dto.BookedTicketRequest;
import com.projekt.ztw_projekt.repositories.AssignmentRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booked_tickets")
public class BookedTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int seatNumber;
    private String code;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;
    private Date movieDate;

    public void updateWithRequest(BookedTicketRequest request, Assignment assignment){
        seatNumber = request.getSeatNumber();
        code = request.getCode();
        this.assignment = assignment;
        movieDate = request.getMovieDate();
    }
}
