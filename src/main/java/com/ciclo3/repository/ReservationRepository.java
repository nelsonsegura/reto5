package com.ciclo3.repository;

import com.ciclo3.model.Client;
import com.ciclo3.model.Reservation;
import com.ciclo3.model.query.CountClient;
import com.ciclo3.repository.crud.ReservationCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class    ReservationRepository {
    @Autowired
    private ReservationCrudRepository reservationCrudRepository;

    public List<Reservation> getAllReservations(){
        return (List<Reservation>) reservationCrudRepository.findAll();
    }
    public Optional<Reservation> getReservationById(Integer id){
        return reservationCrudRepository.findById(id);
    }
    public Reservation saveReservation(Reservation r){
        return reservationCrudRepository.save(r);
    }
    public void deleteReservation(Reservation r){
        reservationCrudRepository.delete(r);
    }

    public List<CountClient> getTopClients(){
        List<CountClient> result = new ArrayList<>();
        List<Object[]> report = reservationCrudRepository.countTotalReservationsByClient();

        for (int i = 0; i < report.size() ; i++) {
            result.add(new CountClient((Long) report.get(i)[1], (Client) report.get(i)[0]));
        }
        return result;
    }

    public List<Reservation> getReservationByPeriod(Date init, Date end){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(init, end);
    }

    public List<Reservation> getReservationByStatus(String status){
        return reservationCrudRepository.findAllByStatus(status);
    }


}
