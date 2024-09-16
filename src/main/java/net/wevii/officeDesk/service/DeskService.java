package net.wevii.officeDesk.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.wevii.officeDesk.domain.Desk;
import net.wevii.officeDesk.repository.DeskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
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

    public void desk(@RequestBody Desk desk,Long deskId) {
        Optional<Desk> deskOp = deskRepository.findById(deskId);

        if (desk.isScreen() != deskOp.get().isScreen()) {
            boolean screen = desk.isScreen();
            deskOp.get().setScreen(screen);
            deskRepository.save(desk);
        } else {
            log.info("there is no desk to change !!!");
        }

    }

    public void deleteDeskById(long deskId) {
        deskRepository.deleteById(deskId);
    }
}
