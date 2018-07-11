package app.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
class ProductManager {

    private final ProductRepository productRepository;

    List<Product> getProductsList() {
        return productRepository.findAll();
    }

    Product getProductByID(Integer id) {
        return productRepository.findById(id).orElse(null);

    }

    void insertProduct(String name, BigDecimal price) {
        productRepository.save(new Product(name, price, null));
    }

    void updateProduct(Integer id, String name, BigDecimal price) {
        productRepository
                .findById(id)
                .ifPresent(product -> {
                    product.setName(name);
                    product.setPrice(price);
                    productRepository.save(product);
                });
    }

    void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}
