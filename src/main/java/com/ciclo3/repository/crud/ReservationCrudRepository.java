package com.ciclo3.repository.crud;

import com.ciclo3.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ReservationCrudRepository extends CrudRepository <Reservation, Integer> {

    @Query("SELECT r.client, COUNT(r.client) FROM Reservation as r GROUP BY r.client ORDER BY COUNT(r.client) DESC")
    public List<Object[]> countTotalReservationsByClient();

    public List<Reservation> findAllByStatus(String status);
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);

}
