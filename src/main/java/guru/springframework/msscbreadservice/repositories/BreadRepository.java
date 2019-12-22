package guru.springframework.msscbreadservice.repositories;

import guru.springframework.msscbreadservice.domain.Bread;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.UUID;

public interface BreadRepository extends PagingAndSortingRepository<Bread, UUID> {
}
