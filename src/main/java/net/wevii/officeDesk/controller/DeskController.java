package net.wevii.officeDesk.controller;

import lombok.AllArgsConstructor;
import net.wevii.officeDesk.domain.Desk;
import net.wevii.officeDesk.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/desk")
public class DeskController {

    @Autowired
    private DeskService deskService;

    @GetMapping()
    public List<Desk> getAllDesk(){
        return  deskService.getAllDesk();
    }

    @GetMapping(value = "{deskId}")
    public Desk deskByID(@PathVariable("deskId") long id){
        return deskService.getDeskByid(id);
    }

    @PostMapping
    public ResponseEntity desk (@RequestBody Desk desk){
        Desk saveDesk = deskService.saveNewDesk(desk);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/desk/" + saveDesk.getId());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("{deskId}")
    public ResponseEntity desk(@PathVariable("deskId") Long deskId,
                                             @RequestBody Desk desk){

        deskService.desk(deskId, desk);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{deskID}")
    public ResponseEntity desk(@PathVariable("deskId") long deskId){

        deskService.deleteDeskById(deskId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
