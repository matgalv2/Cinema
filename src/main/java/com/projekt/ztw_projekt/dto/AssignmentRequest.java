package com.projekt.ztw_projekt.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class AssignmentRequest {
    private Time startsAt;
    private Date startDate;
    private Date endDate;
    private int movieId;
    private int auditoriumId;
}
