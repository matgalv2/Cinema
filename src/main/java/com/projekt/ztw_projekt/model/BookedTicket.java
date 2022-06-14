package com.projekt.ztw_projekt.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;

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

}
