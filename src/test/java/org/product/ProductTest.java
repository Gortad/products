package org.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductTest {

    @Autowired
    public ProductController productController;

    @Test
    public void checkGetProducts() throws Exception {
        List<Product> products = productController.products();
        assertThat(products != null);
        assertThat(products.size() == 0);
    }

    @Test
    public void checkInsertProducts() throws Exception {
        productController.insertProduct("test", new BigDecimal("3.0"));
        productController.insertProduct("test", new BigDecimal("3.0"));
        productController.insertProduct("test", new BigDecimal("3.0"));
        assertThat(productController.products().size() == 3);
    }

    @Test
    public void checkGetExistingProduct() throws Exception {
        productController.insertProduct("test", new BigDecimal("3.0"));
        Product product = productController.product(1);
        assertThat(product != null);
        assertThat(product.getName().equals("test"));
        assertThat(product.getPrice().equals(new BigDecimal("3.0")));
    }

    @Test
    public void checkGetNotExistingProduct() throws Exception {
        assertThat(productController.product(1) == null);
    }

    @Test
    public void checkUpdateProducts() throws Exception {
        productController.insertProduct("test", new BigDecimal("3.0"));
        productController.updateProduct(1, "new name", new BigDecimal("4.5"));
        Product product = productController.product(1);
        assertThat(product != null);
        assertThat(product.getName().equals("new name"));
        assertThat(product.getPrice().equals(new BigDecimal("4.5")));
    }

    @Test
    public void checkDeleteProducts() throws Exception {
        productController.insertProduct("test", new BigDecimal("3.0"));
        productController.insertProduct("test", new BigDecimal("3.0"));
        List<Product> products = productController.products();
        assertThat(productController.products().size() == 2);
        productController.deleteProduct(1);
        assertThat(productController.products().size() == 1);
        productController.deleteProduct(2);
        assertThat(productController.products().size() == 0);
    }
}

