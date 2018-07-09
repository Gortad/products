package org.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductManager productManager;

    @GetMapping(value = "/products")
    public List<Product> products() {
        return productManager.getProductsList();
    }

    @GetMapping(value = "/products/{id}")
    public Product product(@PathVariable Integer id) {
        return productManager.getProductByID(id);
    }

    @PostMapping(value = "/products")
    public void insertProduct(@RequestParam(value = "name") String name, @RequestParam(value = "price") BigDecimal
            price) {
        productManager.insertProduct(name, price);
    }


    @PutMapping(value = "/products/{id}")
    public void updateProduct(@PathVariable Integer id, @RequestParam(value = "name") String name, @RequestParam
            (value = "price") BigDecimal price) {
        productManager.updateProduct(id, name, price);
    }

    @DeleteMapping(value = "/products/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productManager.deleteProduct(id);
    }
}
