package app.product;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class ProductManager {

    private final ProductRepository productRepository;

    List<Product> getProductsList() {
        return productRepository.findAll();
    }

    Product getProductByID(Integer id) {
//        Optional<Product> optionalProduct = productRepository.findById(id);
//        if (optionalProduct.isPresent()) {
//            return optionalProduct.get();
//        }
//        return null;
        //takie cuda nie mają sensu to co jest niżej to jest to samo.
        return productRepository.findById(id).orElse(null);

    }

    void insertProduct(String name, BigDecimal price) {
        Product product = new Product(name, price);
        productRepository.save(product);
    }

    public void updateProduct(Integer id, String name, BigDecimal price) {
//        Optional<Product> optionalProduct = productRepository.findById(id);
//        if (optionalProduct.isPresent()) {
//            Product product = optionalProduct.get();
//            product.setName(name);
//            product.setPrice(price);
//            productRepository.save(product);
//        }
        // lepiej zrobić całkowicie na streamach (raczej nie robimy coś takiego jak isPresent
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
