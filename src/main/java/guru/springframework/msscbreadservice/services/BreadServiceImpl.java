package guru.springframework.msscbreadservice.services;

import guru.springframework.msscbreadservice.domain.Bread;
import guru.springframework.msscbreadservice.repositories.BreadRepository;
import guru.springframework.msscbreadservice.web.controller.NotFoundException;
import guru.springframework.msscbreadservice.web.mappers.BreadMapper;
import guru.springframework.msscbreadservice.web.model.BreadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@RequiredArgsConstructor
@Service//("breadService")
public class BreadServiceImpl implements BreadService {

    private final BreadRepository breadRepository;
    private final BreadMapper breadMapper;

    @Override
    public BreadDto getById(UUID breadId) {
        return breadMapper.breadToBreadDto(
                breadRepository.findById(breadId).orElseThrow(NotFoundException::new)
        );
    }

    @Override
    public BreadDto saveNewBread(BreadDto breadDto) {
        return breadMapper.breadToBreadDto(breadRepository.save(breadMapper.breadDtoToBread(breadDto)));
    }

    @Override
    public BreadDto updateBread(UUID breadId, BreadDto breadDto) {

        Bread bread = breadRepository.findById(breadId).orElseThrow(NotFoundException::new);

        bread.setBreadName(breadDto.getBreadName());
        bread.setBreadStyle(breadDto.getBreadStyle().name());
        bread.setPrice(breadDto.getPrice());
        bread.setUpc(breadDto.getUpc());

        return breadMapper.breadToBreadDto(breadRepository.save(bread));
    }
}
