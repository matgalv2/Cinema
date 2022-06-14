package com.projekt.ztw_projekt.repositories;


import com.projekt.ztw_projekt.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    //    List<Movie> findAll();
//    Optional<Movie> findById(Integer id);
//    boolean existsById(Integer id);
//    Movie save(Movie entity);
//    void deleteById(Integer id);


    /*
     * Jeśli interface dziedziczyłby po JpaRepository
     * i nie chcielibyśmy jakiejś metody stamtąd, wtedy możemy dodać adnotację
     * @RestResource(exported = false)
     * */
}