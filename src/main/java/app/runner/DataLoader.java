package app.runner;

import app.auth.User;
import app.auth.UserRepository;
import app.product.Product;
import app.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("!test")
// Lepiej zrobić loader per pakiet/funkcjonalność - wtedy unikamy upublicznienia wszystkiego.
class DataLoader implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void run(ApplicationArguments args) {
        userRepository.save(new User("user", bCryptPasswordEncoder.encode("12345")));
        productRepository.save(new Product("woda", new BigDecimal("3.0")));
        productRepository.save(new Product("cola", new BigDecimal("6.0")));
    }
}
