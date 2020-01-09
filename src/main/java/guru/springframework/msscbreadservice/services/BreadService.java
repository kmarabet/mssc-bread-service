package guru.springframework.msscbreadservice.services;

import guru.springframework.msscbreadservice.domain.Bread;
import guru.springframework.msscbreadservice.web.model.BreadDto;
import guru.springframework.msscbreadservice.web.model.BreadPagedList;
import guru.springframework.msscbreadservice.web.model.BreadStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.UUID;

public interface BreadService {

    Page<Bread> listBreads(PageRequest pageRequest);

    BreadPagedList listBreads(String breadName, BreadStyleEnum breadStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

    BreadDto getById(UUID breadId);

    BreadDto saveNewBread(BreadDto breadDto);

    BreadDto updateBread(UUID breadId, BreadDto breadDto);
}
