package guru.springframework.msscbreadservice.services;

import guru.springframework.msscbreadservice.config.JmsConfig;
import guru.springframework.msscbreadservice.domain.Bread;
import guru.springframework.msscbreadservice.repositories.BreadRepository;
import guru.springframework.msscbreadservice.services.inventory.BreadInventoryService;
import guru.springframework.msscbreadservice.web.mappers.BreadMapper;
import guru.springframework.msscbreadservice.web.model.events.BakeBreadEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BakingService {

    private final BreadRepository breadRepository;
    private final BreadInventoryService breadInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BreadMapper breadMapper;

    //@Scheduled(fixedRate = 5000) //every 5 seconds
    public void checkForLowInventory() {
        List<Bread> breads = breadRepository.findAll();

        breads.forEach(bread -> {
            Integer invQOH = breadInventoryService.getOnhandInventory(bread.getId());

            log.debug("Min Onhand is: "+bread.getMinOnHand());
            log.debug("Inventory is: "  + invQOH);

            if(bread.getMinOnHand() >= invQOH){
                jmsTemplate.convertAndSend(JmsConfig.BAKING_REQUEST_QUEUE, new BakeBreadEvent(breadMapper.breadToBreadDto(bread)));
            }
        });
    }
}
