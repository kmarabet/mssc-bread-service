package guru.springframework.msscbreadservice.services.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Created by jt on 2019-06-07.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BreadInventoryDto {
    private UUID id;
    private OffsetDateTime createdDate;
    private OffsetDateTime lastModifiedDate;
    private UUID breadId;
    private Integer quantityOnHand;
}
