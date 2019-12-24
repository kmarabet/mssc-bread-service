package guru.springframework.msscbreadservice.web.mappers;

import guru.springframework.msscbreadservice.domain.Bread;
import guru.springframework.msscbreadservice.web.model.BreadDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BreadMapper {

    BreadDto breadToBreadDto(Bread bread);

    Bread breadDtoToBread(BreadDto breadDto);

}
