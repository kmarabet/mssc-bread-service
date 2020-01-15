package guru.springframework.msscbreadservice.services.inventory;

import guru.springframework.msscbreadservice.services.inventory.model.BreadInventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by jt on 2019-06-07.
 */
@Slf4j
@ConfigurationProperties(prefix = "sfg.bakery", ignoreUnknownFields = false)
@Component
public class BreadInventoryServiceRestTemplateImpl implements BreadInventoryService {

    private final String INVENTORY_PATH = "/api/v1/bread/{breadId}/inventory";
    private final RestTemplate restTemplate;

    private String breadInventoryServiceHost;

    public void setBreadInventoryServiceHost(String breadInventoryServiceHost) {
        this.breadInventoryServiceHost = breadInventoryServiceHost;
    }

    public BreadInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Integer getOnhandInventory(UUID breadId) {

        log.debug("Calling Inventory Service");

        ResponseEntity<List<BreadInventoryDto>> responseEntity = restTemplate
                .exchange(breadInventoryServiceHost + INVENTORY_PATH, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<BreadInventoryDto>>(){}, (Object) breadId);

        //sum from inventory list
        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BreadInventoryDto::getQuantityOnHand)
                .sum();

        return onHand;
    }
}
