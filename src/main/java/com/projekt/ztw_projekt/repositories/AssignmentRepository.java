package com.projekt.ztw_projekt.repositories;

import com.projekt.ztw_projekt.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
//    List<Assignment> findAll();
//    Optional<Assignment> findById(Integer id);
//    boolean existsById(Integer id);
//    Assignment save(Assignment entity);
//    void deleteById(Integer id);
}
