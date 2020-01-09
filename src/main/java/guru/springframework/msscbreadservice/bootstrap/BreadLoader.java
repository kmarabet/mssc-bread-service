package guru.springframework.msscbreadservice.bootstrap;

import guru.springframework.msscbreadservice.domain.Bread;
import guru.springframework.msscbreadservice.repositories.BreadRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

//@Component
public class BreadLoader implements CommandLineRunner {

    public static final String BREAD_1_UPC = "781421006108";
    public static final String BREAD_2_UPC = "781421002056";
    public static final String BREAD_3_UPC = "781421002059";

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
                    .upc(BREAD_1_UPC)
                    .price(new BigDecimal("1.65"))
                    .build());

            breadRepository.save(Bread.builder()
                    .breadName("Country White Sourdough Oval")
                    .breadStyle("White")
                    .size(12.50f)
                    .sizeUnity("oz")
                    .upc(BREAD_2_UPC)
                    .price(new BigDecimal("1.95"))
                    .build());

            breadRepository.save(Bread.builder()
                    .breadName("Country White Sourdough Oval")
                    .breadStyle("White")
                    .size(11.50f)
                    .sizeUnity("oz")
                    .upc(BREAD_3_UPC)
                    .price(new BigDecimal("1.9"))
                    .minOnHand(100)
                    .build());
        }
        System.out.println("Loaded Breads: " + breadRepository.count());
    }
}
