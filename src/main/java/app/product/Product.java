package app.product;

// import lombok.*; nie stosujemy w ustawieniach intellija możemy ustawić od ilu importoów powinien zwijać

// import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;


@Entity
@Data
@NoArgsConstructor
//https://stackoverflow.com/a/48903136 - można się pokusić o coś takiego, wtedy nie musisz ręcznie pisać
// konstruktorów, a ID jest odpowiednio wydzielone.
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, BigDecimal price, ProductCategory productCategory) {
        this.name = name;
        this.price = price;
        this.productCategory = productCategory;
    }
}
