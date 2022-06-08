package com.projekt.ztw_projekt.repositories;

import com.projekt.ztw_projekt.model.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuditoriumRepository extends JpaRepository<Auditorium, Integer> {

//    List<Auditorium> findAll();
//    Optional<Auditorium> findById(Integer id);
//    boolean existsById(Integer id);
//    Auditorium save(Auditorium entity);
//    void deleteById(Integer id);

}
