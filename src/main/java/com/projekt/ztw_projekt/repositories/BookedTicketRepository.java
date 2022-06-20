package com.projekt.ztw_projekt.repositories;

import com.projekt.ztw_projekt.model.BookedTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookedTicketRepository extends JpaRepository<BookedTicket, Integer> {

    List<BookedTicket> findAllByCode(String code);
    List<BookedTicket> findAllByAssignmentId(int id);
}
