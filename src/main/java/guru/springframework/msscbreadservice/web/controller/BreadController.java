package guru.springframework.msscbreadservice.web.controller;

import guru.springframework.msscbreadservice.services.BreadService;
import guru.springframework.msscbreadservice.web.model.BreadDto;
import guru.springframework.msscbreadservice.web.model.BreadPagedList;
import guru.springframework.msscbreadservice.web.model.BreadStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/bread")
@RestController
public class BreadController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final BreadService breadService;

//    @GetMapping
//    public ResponseEntity<Page<Bread>> getBreads(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
//                                           @RequestParam(value = "pageSize", required = false) Integer pageSize){
//
//        if (pageNumber == null || pageNumber < 0){
//            pageNumber = DEFAULT_PAGE_NUMBER;
//        }
//        if (pageSize == null || pageSize < 1) {
//            pageSize = DEFAULT_PAGE_SIZE;
//        }
//
//        Page<Bread> breadList = breadService.listBreads( PageRequest.of(pageNumber, pageSize));
//
//        return new ResponseEntity<>(breadList, HttpStatus.OK);
//    }

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<BreadPagedList> getBreads(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                              @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                              @RequestParam(value = "breadName", required = false) String breadName,
                                              @RequestParam(value = "breadStyle", required = false) BreadStyleEnum breadStyle,
                                              @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand){

        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }

        if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        BreadPagedList breadList = breadService.listBreads(breadName, breadStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);

        return new ResponseEntity<>(breadList, HttpStatus.OK);
    }

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
