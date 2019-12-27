package guru.springframework.msscbreadservice.web.controller;

import guru.springframework.msscbreadservice.services.BreadService;
import guru.springframework.msscbreadservice.web.model.BreadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/bread")
@RestController
public class BreadController {

    private final BreadService breadService;

    @GetMapping("/{breadId}")
    public ResponseEntity<BreadDto> getBreadById(@PathVariable("breadId") UUID breadId){

        return new ResponseEntity<>(breadService.getById(breadId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBread(@RequestBody @Validated BreadDto breadDto){

        return new ResponseEntity<>(breadService.saveNewBread(breadDto), HttpStatus.CREATED);
    }

    @PutMapping("/{breadId}")
    public ResponseEntity updateBreadId(@PathVariable("breadId") UUID breadId, @RequestBody @Validated BreadDto breadDto){

        return new ResponseEntity<>(breadService.updateBread(breadId, breadDto), HttpStatus.NO_CONTENT);
    }

}
