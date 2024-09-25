package net.wevii.officeDesk.service;

import lombok.AllArgsConstructor;
import net.wevii.officeDesk.domain.Reservation;
import net.wevii.officeDesk.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationService {

    @Autowired
    private final ReservationRepository reservationRepository;

    public void getAllReservation() {
        reservationRepository.findAll();
    }

    public void getReservationById(long reservationId) {
        reservationRepository.getReferenceById(reservationId);
    }

    public void saveNewReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void deleteReservation(long id) {
        reservationRepository.deleteById(id);
    }

    public void changeReservation(Long id, Reservation reservation) {
        Optional<Reservation> reservationOp = reservationRepository.findById(id);
        if(reservationOp.get().getId() != reservation.getId()){
            if(reservationOp.get().getDate() != reservation.getDate()){
                reservationOp.get().setDate(reservation.getDate());
            }
            if(reservationOp.get().getDesk() != reservation.getDesk()){
                reservationOp.get().setDesk(reservation.getDesk());
            }
        }
        reservationRepository.save(reservation);
    }
}
