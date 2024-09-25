package net.wevii.officeDesk.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    private final DeskService deskService;

    @ApiResponses({
            @ApiResponse(responseCode = "302", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Desk.class)) }),
            @ApiResponse(responseCode = "404", description = "Desk not found",
                    content = @Content) })
    @Tag(name = "get", description = "GET ALL methods of Desk's APIs")
    @GetMapping()
    public List<Desk> getAllDesk(){
        return  deskService.getAllDesk();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "302", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Desk.class)) }),
            @ApiResponse(responseCode = "404", description = "Desk not found",
                    content = @Content) })
    @Tag(name = "get", description = "GET methods of Desk's APIs")
    @GetMapping(value = "{deskId}")
    public Desk deskByID(@PathVariable("deskId") long id){
        return deskService.getDeskByid(id);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Desk.class)) }),
            @ApiResponse(responseCode = "404", description = "Desk not create",
                    content = @Content) })
    @Tag(name = "post", description = "POST methods of Desk's APIs")
    @PostMapping
    public ResponseEntity desk (@RequestBody Desk desk){
        desk.setAvailable(desk.isAvailable());
        desk.setScreen(desk.isScreen());
        Desk saveDesk = deskService.saveNewDesk(desk);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/desk/" + saveDesk.getId());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Desk.class)) }),
            @ApiResponse(responseCode = "404", description = "Desk not changed",
                    content = @Content) })
    @Tag(name = "put", description = "PUT methods of Desk's APIs")
    @PutMapping("{deskId}")
    public ResponseEntity desk(@PathVariable("deskId") Long deskId,
                                             @RequestBody Desk desk){

        deskService.desk(desk, deskId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Desk.class)) }),
            @ApiResponse(responseCode = "404", description = "Desk not deleted",
                    content = @Content) })
    @Tag(name = "delete", description = "DELETE methods of Desk's APIs")
    @DeleteMapping("{deskId}")
    public ResponseEntity desk(@PathVariable("deskId") long deskId){

        deskService.deleteDeskById(deskId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
