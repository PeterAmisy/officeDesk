package net.wevii.officeDesk.controller;

import lombok.AllArgsConstructor;
import net.wevii.officeDesk.domain.Reservation;
import net.wevii.officeDesk.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservation(){
        reservationService.getAllReservation();
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

    @GetMapping(value = "{reservationId}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("reservationId") long id){
        reservationService.getReservationById(id);
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Reservation> reservation(@RequestBody Reservation reservation){
        reservationService.saveNewReservation(reservation);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "{reservationId}")
    public ResponseEntity<Reservation> ChangeReservation(@PathVariable ("reservationId")Long id,
                                                             @RequestBody Reservation reservation){
        reservationService.changeReservation(id, reservation);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "{reservationId}")
    public ResponseEntity<Reservation> reservation (@PathVariable("reservationId")long id){
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
