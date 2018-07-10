package app;

import app.auth.User;
import app.auth.UserRepository;
import app.product.Product;
import app.product.ProductCategory;
import app.product.ProductCategoryRepository;
import app.product.ProductController;
import app.product.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
//jak spakietujesz test zgodnie z pakietami w mainie - to też unikniesz upublicznienia wszystkiego
@Slf4j
public class ApplicationTest {

    //@Slf4j
    //private static final Logger logger = LoggerFactory.getLogger(ApplicationTest.class);

    @Autowired
    private ProductController productController;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void testGetProducts() throws Exception {
        List<Product> products = productController.products();
        assertThat(products != null);
        assertThat(products.size() == 0);
    }

    @Test
    public void testInsertProducts() throws Exception {
        productController.insertProduct("test", new BigDecimal("3.0"));
        productController.insertProduct("test", new BigDecimal("3.0"));
        productController.insertProduct("test", new BigDecimal("3.0"));
        assertThat(productController.products().size() == 3);
    }

    @Test
    public void testGetExistingProduct() throws Exception {
        productController.insertProduct("test", new BigDecimal("3.0"));
        Product product = productController.product(1);
        assertThat(product != null);
        assertThat(product.getName().equals("test"));
        assertThat(product.getPrice().equals(new BigDecimal("3.0")));
    }

    @Test
    public void testGetNotExistingProduct() throws Exception {
        assertThat(productController.product(1) == null);
    }

    @Test
    public void testUpdateProducts() throws Exception {
        productController.insertProduct("test", new BigDecimal("3.0"));
        productController.updateProduct(1, "new name", new BigDecimal("4.5"));
        Product product = productController.product(1);
        assertThat(product != null);
        assertThat(product.getName().equals("new name"));
        assertThat(product.getPrice().equals(new BigDecimal("4.5")));
    }

    @Test
    public void testDeleteProducts() throws Exception {
        productController.insertProduct("test", new BigDecimal("3.0"));
        productController.insertProduct("test", new BigDecimal("3.0"));
        assertThat(productController.products().size() == 2);
        productController.deleteProduct(1);
        assertThat(productController.products().size() == 1);
        productController.deleteProduct(2);
        assertThat(productController.products().size() == 0);
    }

    @Test
    public void testProductCategory() throws Exception {
        ProductCategory category = new ProductCategory("drink");
        productCategoryRepository.save(category);
        productRepository.save(new Product("water", new BigDecimal("3.0"), category));
        productRepository.save(new Product("cola", new BigDecimal("6.0"), category));
        category = new ProductCategory("metal");
        productCategoryRepository.save(category);
        productRepository.save(new Product("iron", new BigDecimal("30.0"), category));
        assertThat(productRepository.findAll().size() == 3);
        assertThat(productCategoryRepository.findAll().size() == 3);
        Optional<ProductCategory> optionalCategory = productCategoryRepository.findById(1);
        assertThat(optionalCategory.isPresent());
        assertThat(optionalCategory.get().getName().equals("drink"));
        List<Product> products = optionalCategory.get().getProducts();
        assertThat(products.size() == 2);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testNonExistingUser() throws Exception {
        userDetailsService.loadUserByUsername("user");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testNonExistingUserAfterInsert() throws Exception {
        userRepository.save(new User("user", bCryptPasswordEncoder.encode("12345")));
        userDetailsService.loadUserByUsername("user2");
    }


    @Test
    public void testUsers() throws Exception {
        userRepository.save(new User("user", bCryptPasswordEncoder.encode("12345")));
        UserDetails userDetails = userDetailsService.loadUserByUsername("user");
        assertThat(userDetails != null);
        assertThat(userDetails.getUsername().equals("user"));
        assertThat(userDetails.getPassword().equals("12345"));
    }
}

