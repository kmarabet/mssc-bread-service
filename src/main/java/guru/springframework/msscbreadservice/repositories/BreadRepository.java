package guru.springframework.msscbreadservice.repositories;

import guru.springframework.msscbreadservice.domain.Bread;
import guru.springframework.msscbreadservice.web.model.BreadStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BreadRepository extends JpaRepository<Bread, UUID> {

    Page<Bread> findAllByBreadName(String breadName, Pageable pageable);

    Page<Bread> findAllByBreadStyle(BreadStyleEnum breadStyle, Pageable pageable);

    Page<Bread> findAllByBreadNameAndBreadStyle(String breadName, BreadStyleEnum breadStyle, Pageable pageable);

    Bread findByUpc(String upc);
    
}
