package guru.springframework.msscbreadservice.web.controller;

import guru.springframework.msscbreadservice.web.model.BreadDto;
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

@RequestMapping("/api/v1/bread")
@RestController
public class BreadController {

    @GetMapping("/{breadId}")
    public ResponseEntity<BreadDto> getBreadById(@PathVariable("breadId") UUID breadId){
        //todo impl
        return new ResponseEntity<>(BreadDto.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBread(@RequestBody @Validated BreadDto breadDto){
        //todo impl
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{breadId}")
    public ResponseEntity updateBreadId(@PathVariable("breadId") UUID breadId, @RequestBody @Validated BreadDto breadDto){

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
