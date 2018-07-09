package org.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class ProductManager {

    private ProductRepository productRepository;

    @Autowired
    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductsList() {
        return productRepository.findAll();
    }

    public Product getProductByID(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        return null;
    }

    public void insertProduct(String name, BigDecimal price) {
        Product product = new Product(name, price);
        productRepository.save(product);
    }

    public void updateProduct(Integer id, String name, BigDecimal price) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(name);
            product.setPrice(price);
            productRepository.save(product);
        }
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}
