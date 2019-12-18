package guru.springframework.msscbreadservice.web.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BreadPagedList extends PageImpl<BreadDto> {

    public BreadPagedList(List<BreadDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BreadPagedList(List<BreadDto> content) {
        super(content);
    }
}
