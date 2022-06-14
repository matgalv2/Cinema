package com.projekt.ztw_projekt.repositories;

import com.projekt.ztw_projekt.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    boolean existsByAuditoriumIdAndStartsAt(Integer auditoriumID, Time startsAt);
    boolean existsByMovieIdAndStartsAt(Integer movieID, Time startsAt);
    List<Assignment> findByMovieId(int id);
    Assignment findByMovieIdAndStartsAt(int id, Time startsAt);
}
