package net.wevii.officeDesk.service;

import lombok.AllArgsConstructor;
import net.wevii.officeDesk.domain.Desk;
import net.wevii.officeDesk.repository.DeskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class DeskService {

    @Autowired
    private DeskRepository deskRepository;

    public List<Desk> getAllDesk() {
        return deskRepository.findAll();
    }

    public Desk getDeskByid(long id) {
        return deskRepository.getReferenceById(id);
    }

    public Desk saveNewDesk(Desk desk) {
        return deskRepository.save(desk);
    }

    public void desk(Long deskId, Desk desk) {
        Desk deskOp = deskRepository.findById(deskId).get();
        if(desk.isAvailable() != deskOp.isAvailable()){
            deskOp.setAvailable(desk.isAvailable());
        }
    }

    public void deleteDeskById(long deskId) {
        deskRepository.deleteById(deskId);
    }
}
