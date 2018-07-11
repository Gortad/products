package app.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("!test")
class ProductDataLoader implements ApplicationRunner {

    @Autowired
    private ProductRepository productRepository;

    public void run(ApplicationArguments args) {
        productRepository.save(new Product("woda", new BigDecimal("3.0"), null));
        productRepository.save(new Product("cola", new BigDecimal("6.0"), null));
    }
}
