package guru.springframework.msscbreadservice.web.model.events;

import guru.springframework.msscbreadservice.web.model.BreadDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by jt on 2019-07-21.
 */
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class BreadEvent implements Serializable {

    static final long serialVersionUID = -5781515597148163111L;

    private final BreadDto breadDto;
}
