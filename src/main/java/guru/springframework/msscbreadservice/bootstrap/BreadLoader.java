package guru.springframework.msscbreadservice.bootstrap;

import guru.springframework.msscbreadservice.domain.Bread;
import guru.springframework.msscbreadservice.repositories.BreadRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class BreadLoader implements CommandLineRunner {

    private final BreadRepository breadRepository;

    public BreadLoader(BreadRepository breadRepository) {
        this.breadRepository = breadRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBreadObjects();
    }

    private void loadBreadObjects() {
        if (breadRepository.count() == 0) {

            breadRepository.save(Bread.builder()
                    .breadName("Baguette")
                    .breadStyle("French")
                    .size(14.50f)
                    .sizeUnity("oz")
                    .upc(781421006108L)
                    .price(new BigDecimal("1.65"))
                    .build());

            breadRepository.save(Bread.builder()
                    .breadName("Country White Sourdough Oval")
                    .breadStyle("White")
                    .size(12.50f)
                    .sizeUnity("oz")
                    .upc(781421002056L)
                    .price(new BigDecimal("1.95"))
                    .build());
        }
        System.out.println("Loaded Breads: " + breadRepository.count());
    }
}
