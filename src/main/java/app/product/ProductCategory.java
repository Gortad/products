package app.product;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_category")
@Data
@NoArgsConstructor
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique=true)
    private String name;

    @OneToMany(mappedBy = "productCategory", fetch = FetchType.EAGER)
    private List<Product> products;

    public ProductCategory(String name) {
        this.name = name;
    }
}
