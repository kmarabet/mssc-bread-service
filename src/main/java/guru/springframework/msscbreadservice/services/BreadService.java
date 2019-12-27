package guru.springframework.msscbreadservice.services;

import guru.springframework.msscbreadservice.web.model.BreadDto;
import java.util.UUID;

public interface BreadService {

    BreadDto getById(UUID breadId);

    BreadDto saveNewBread(BreadDto breadDto);

    BreadDto updateBread(UUID breadId, BreadDto breadDto);
}
