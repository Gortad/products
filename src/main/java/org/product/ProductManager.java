package org.product;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductManager {
    private List<Product> products = new ArrayList<>();

    private Integer id = 0;

    public List<Product> getProductsList() {
        return new ArrayList<>(products);
    }

    public Product getProductByID(Integer id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }

        return null;
    }

    public void insertProduct(String name, BigDecimal price) {
        id += 1;
        products.add(new Product(id, name, price));
    }

    public void updateProduct(Integer id, String name, BigDecimal price) {
        for (int i = 0; i < products.size(); ++i) {
            if (products.get(i).getId().equals(id)) {
                products.set(i, new Product(id, name, price));
                return;
            }
        }
    }

    public void deleteProduct(Integer id) {
        for (int i = 0; i < products.size(); ++i) {
            if (products.get(i).getId().equals(id)) {
                products.remove(i);
                return;
            }
        }
    }
}
