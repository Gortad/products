package app.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
class ProductController {

    private final ProductManager productManager;

    @GetMapping(value = "/products")
    List<Product> products() {
        return productManager.getProductsList();
    }

    @GetMapping(value = "/products/{id}")
    Product product(@PathVariable Integer id) {
        return productManager.getProductByID(id);
    }

    @PostMapping(value = "/products")
    void insertProduct(@RequestParam(value = "name") String name, @RequestParam(value = "price") BigDecimal
            price) {
        productManager.insertProduct(name, price);
    }


    @PutMapping(value = "/products/{id}")
    void updateProduct(@PathVariable Integer id, @RequestParam(value = "name") String name, @RequestParam
            (value = "price") BigDecimal price) {
        productManager.updateProduct(id, name, price);
    }

    @DeleteMapping(value = "/products/{id}")
    void deleteProduct(@PathVariable Integer id) {
        productManager.deleteProduct(id);
    }
}
