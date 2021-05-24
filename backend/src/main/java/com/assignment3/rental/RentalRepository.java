package com.assignment3.rental;

import com.assignment3.car.model.Car;
import com.assignment3.rental.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Query("SELECT r from Rental r where (r.car = ?1) and ((r.fromDate < ?2 and r.untilDate > ?2) or (r.fromDate > ?2 and r.untilDate < ?3) or (r.fromDate < ?3 and r.untilDate > ?3))")
    List<Rental> findIfNotFreeSlot(Car car, LocalDate from, LocalDate until);
}
