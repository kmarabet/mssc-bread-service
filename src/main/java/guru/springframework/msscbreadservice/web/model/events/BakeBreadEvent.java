package guru.springframework.msscbreadservice.web.model.events;

import guru.springframework.msscbreadservice.web.model.BreadDto;

/**
 * Created by jt on 2019-07-21.
 */
//@NoArgsConstructor
public class BakeBreadEvent extends BreadEvent {

    public BakeBreadEvent(BreadDto breadDto) {
        super(breadDto);
    }
}
