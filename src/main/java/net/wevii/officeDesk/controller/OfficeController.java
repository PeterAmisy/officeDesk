package net.wevii.officeDesk.controller;

import lombok.AllArgsConstructor;
import net.wevii.officeDesk.domain.Desk;
import net.wevii.officeDesk.domain.Office;
import net.wevii.officeDesk.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/office")
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @GetMapping()
    public List<Office> getAllOffice(){
        return  officeService.getAllOffice();
    }

    @GetMapping(value = "{deskId}")
    public Optional<Office> deskByID(@PathVariable("officeId") long id){
        return officeService.getOfficeByid(id);
    }

    @PostMapping()
    public ResponseEntity office (@RequestBody Office office){
        Office saveOffice = officeService.saveNewOffice(office);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/office/" + saveOffice.getId());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("{officeId}")
    public ResponseEntity officeChange(@PathVariable("officeId") Long officeId,
                               @RequestBody Office office){

        officeService.officeChange(officeId,office);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{officeId}")
    public ResponseEntity office(@PathVariable("officeId") long officeId){

        officeService.deleteOfficeById(officeId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}