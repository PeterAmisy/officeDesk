package net.wevii.officeDesk.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    private final OfficeService officeService;

    @ApiResponses({
            @ApiResponse(responseCode = "302", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Office.class)) }),
            @ApiResponse(responseCode = "404", description = "Office not found",
                    content = @Content) })
    @Tag(name = "get", description = "GET ALL methods of Office's APIs")
    @GetMapping()
    public List<Office> getAllOffice(){
        return  officeService.getAllOffice();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "302", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Office.class)) }),
            @ApiResponse(responseCode = "404", description = "Office not found",
                    content = @Content) })
    @Tag(name = "get", description = "GET by ID methods of Office's APIs")
    @GetMapping(value = "{deskId}")
    public Optional<Office> deskByID(@PathVariable("officeId") long id){
        return officeService.getOfficeByid(id);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Office.class)) }),
            @ApiResponse(responseCode = "404", description = "Office not created",
                    content = @Content) })
    @Tag(name = "post", description = "POST methods of Office's APIs")
    @PostMapping()
    public ResponseEntity office (@RequestBody Office office){
        Office saveOffice = officeService.saveNewOffice(office);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/office/" + saveOffice.getId());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Office.class)) }),
            @ApiResponse(responseCode = "404", description = "Desk not changed",
                    content = @Content) })
    @Tag(name = "put", description = "PUT methods of Office's APIs")
    @PutMapping("{officeId}")
    public ResponseEntity officeChange(@PathVariable("officeId") Long officeId,
                               @RequestBody Office office){

        officeService.officeChange(officeId,office);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Office.class)) }),
            @ApiResponse(responseCode = "404", description = "Desk not deleted",
                    content = @Content) })
    @Tag(name = "delete", description = "DELETE methods of Office's APIs")
    @DeleteMapping("{officeId}")
    public ResponseEntity office(@PathVariable("officeId") long officeId){

        officeService.deleteOfficeById(officeId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}