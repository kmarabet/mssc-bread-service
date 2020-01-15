package guru.springframework.msscbreadservice.services.inventory;

import java.util.UUID;

/**
 * Created by jt on 2019-06-07.
 */
public interface BreadInventoryService {

    Integer getOnhandInventory(UUID breadId);
}
