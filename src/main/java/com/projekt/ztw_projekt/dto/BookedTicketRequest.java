package com.projekt.ztw_projekt.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class BookedTicketRequest {
    private int seatNumber;
    private String code;
    private int movieId;
    private Time startsAt;
    private Date movieDate;
}
