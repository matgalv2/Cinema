package com.projekt.ztw_projekt.repositories;

import com.projekt.ztw_projekt.model.BookedTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookedTicketRepository extends JpaRepository<BookedTicket, Integer> {

//    List<BookedTicket> findAll();
//    Optional<BookedTicket> findById(Integer id);
//    boolean existsById(Integer id);
//    BookedTicket save(BookedTicket entity);
//    void deleteById(Integer id);

}
