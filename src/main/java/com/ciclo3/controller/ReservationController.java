package com.ciclo3.controller;

import com.ciclo3.model.Reservation;
import com.ciclo3.model.query.CountClient;
import com.ciclo3.model.query.StatusAmount;
import com.ciclo3.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getAllReservation() {
        return (List<Reservation>) reservationService.getAllReservations();
    }
    @GetMapping("/{id}")
    public Optional<Reservation> getReservationById(@PathVariable Integer id) {
        return reservationService.getReservationById(id);
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation saveReservation(@RequestBody Reservation r) {
        return reservationService.saveReservation(r);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED )
    public Reservation updateReservation(@RequestBody Reservation r) {
        return reservationService.updateReservation(r);
    };
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteReservation(@PathVariable Integer id){
        return reservationService.deleteReservation(id);
    }

    @GetMapping("/report-clients")
    public List<CountClient> getReservationsReport(){
        return reservationService.getTopClients();
    }
    @GetMapping("/report-status")
    public StatusAmount getReservationStatus(){
        return reservationService.getReservationByStatus();
    }

    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getReservationPeriod(@PathVariable("dateOne") String dateOne,@PathVariable("dateTwo") String dateTwo ) {
        return reservationService.getReservationByPeriod(dateOne, dateTwo);
    }
}
