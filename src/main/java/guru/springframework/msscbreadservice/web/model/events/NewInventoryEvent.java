package guru.springframework.msscbreadservice.web.model.events;


import guru.springframework.msscbreadservice.web.model.BreadDto;

/**
 * Created by jt on 2019-07-21.
 */
//@NoArgsConstructor
public class NewInventoryEvent extends BreadEvent {
    public NewInventoryEvent(BreadDto breadDto) {
        super(breadDto);
    }
}
