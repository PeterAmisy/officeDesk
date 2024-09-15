package net.wevii.officeDesk.service;

import lombok.AllArgsConstructor;
import net.wevii.officeDesk.domain.Desk;
import net.wevii.officeDesk.domain.Office;
import net.wevii.officeDesk.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    public List<Office> getAllOffice() {
        return (List<Office>) officeRepository.findAll();
    }

    public Optional<Office> getOfficeByid(long id) {
        return officeRepository.findById(id);
    }

    public Office saveNewOffice(Office office) {
        return officeRepository.save(office);
    }

    public void office(Long officeId, Office office) {
        Office officeOp = officeRepository.findById(officeId).get();
        if(!Objects.equals(office.getName(), officeOp.getName())){
            officeOp.setName(office.getName());
        }
    }

    public void deleteOfficeById(long officeId) {
        officeRepository.deleteById(officeId);
    }
}

