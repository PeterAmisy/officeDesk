package net.wevii.officeDesk.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.wevii.officeDesk.domain.Office;
import net.wevii.officeDesk.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class OfficeService {

    @Autowired
    private final OfficeRepository officeRepository;

    public List<Office> getAllOffice() {
        return (List<Office>) officeRepository.findAll();
    }

    public Optional<Office> getOfficeByid(long id) {
        return officeRepository.findById(id);
    }

    public Office saveNewOffice(Office office) {
        try{
            return officeRepository.save(office);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void officeChange(Long officeId, Office office) {
        Optional<Office> officeOp = officeRepository.findById(officeId);
        try {
            if (!Objects.equals(office.getName(), officeOp.get().getName())) {
                String name = office.getName();
                officeOp.get().setName(name);
            }
            if (!Objects.equals(office.getNumberOfDesk(), officeOp.get().getNumberOfDesk())) {
                int numOfDesk = office.getNumberOfDesk();
                officeOp.get().setNumberOfDesk(numOfDesk);
            }
            officeRepository.save(office);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        log.info("the office " + officeOp.get().getId() + " has change!!!");
    }

    public void deleteOfficeById(long officeId) {
        officeRepository.deleteById(officeId);
        log.info(" the office number " + officeId + "has been delete");
    }
}

