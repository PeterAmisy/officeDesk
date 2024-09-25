package net.wevii.officeDesk.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.wevii.officeDesk.domain.Desk;
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
    private final ReservationService reservationService;

    @ApiResponses({
            @ApiResponse(responseCode = "302", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Reservation.class)) }),
            @ApiResponse(responseCode = "404", description = "Reservation not found",
                    content = @Content) })
    @Tag(name = "get", description = "GET ALL methods of Reservation's APIs")
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservation(){
        reservationService.getAllReservation();
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "302", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Reservation.class)) }),
            @ApiResponse(responseCode = "404", description = "Reservation not found",
                    content = @Content) })
    @Tag(name = "GET", description = "GET by ID methods of Reservation's APIs")
    @GetMapping(value = "{reservationId}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("reservationId") long id){
        reservationService.getReservationById(id);
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Reservation.class)) }),
            @ApiResponse(responseCode = "404", description = "Reservation not created",
                    content = @Content) })
    @Tag(name = "post", description = "POST methods of Reservation's APIs")
    @PostMapping
    public ResponseEntity<Reservation> reservation(@RequestBody Reservation reservation){
        reservationService.saveNewReservation(reservation);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Reservation.class)) }),
            @ApiResponse(responseCode = "404", description = "Reservation not changed",
                    content = @Content) })
    @Tag(name = "put", description = "PUT methods of Reservation's APIs")
    @PutMapping(value = "{reservationId}")
    public ResponseEntity<Reservation> ChangeReservation(@PathVariable ("reservationId")Long id,
                                                             @RequestBody Reservation reservation){
        reservationService.changeReservation(id, reservation);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Reservation.class)) }),
            @ApiResponse(responseCode = "404", description = "Reservation not found",
                    content = @Content) })
    @Tag(name = "delete", description = "DELETE methods of Reservation's APIs")
    @DeleteMapping(value = "{reservationId}")
    public ResponseEntity<Reservation> reservation (@PathVariable("reservationId")long id){
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
