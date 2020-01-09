package guru.springframework.msscbreadservice.services;

import guru.springframework.msscbreadservice.domain.Bread;
import guru.springframework.msscbreadservice.repositories.BreadRepository;
import guru.springframework.msscbreadservice.web.controller.NotFoundException;
import guru.springframework.msscbreadservice.web.mappers.BreadMapper;
import guru.springframework.msscbreadservice.web.model.BreadDto;
import guru.springframework.msscbreadservice.web.model.BreadPagedList;
import guru.springframework.msscbreadservice.web.model.BreadStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service//("breadService")
public class BreadServiceImpl implements BreadService {

    private final BreadRepository breadRepository;
    private final BreadMapper breadMapper;

    @Override
    public Page<Bread> listBreads(PageRequest pageRequest) {

        return breadRepository.findAll(pageRequest);

    }

    @Cacheable(cacheNames = "breadListCache", condition = "#showInventoryOnHand == false ")
    @Override
    public BreadPagedList listBreads(String breadName, BreadStyleEnum breadStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {

        BreadPagedList breadPagedList;
        Page<Bread> breadPage;

        if (!StringUtils.isEmpty(breadName) && !StringUtils.isEmpty(breadStyle)) {
            //search both
            breadPage = breadRepository.findAllByBreadNameAndBreadStyle(breadName, breadStyle, pageRequest);
        } else if (!StringUtils.isEmpty(breadName) && StringUtils.isEmpty(breadStyle)) {
            //search bread_service name
            breadPage = breadRepository.findAllByBreadName(breadName, pageRequest);
        } else if (StringUtils.isEmpty(breadName) && !StringUtils.isEmpty(breadStyle)) {
            //search bread_service style
            breadPage = breadRepository.findAllByBreadStyle(breadStyle, pageRequest);
        } else {
            breadPage = breadRepository.findAll(pageRequest);
        }
        if (showInventoryOnHand){
            breadPagedList = new BreadPagedList(breadPage
                    .getContent()
                    .stream()
                    .map(breadMapper::breadToBreadDtoWithInventory)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(breadPage.getPageable().getPageNumber(),
                                    breadPage.getPageable().getPageSize()),
                    breadPage.getTotalElements());
        } else {
            breadPagedList = new BreadPagedList(breadPage
                    .getContent()
                    .stream()
                    .map(breadMapper::breadToBreadDto)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(breadPage.getPageable().getPageNumber(),
                                    breadPage.getPageable().getPageSize()),
                    breadPage.getTotalElements());
        }

        return breadPagedList;
    }

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
