package br.edu.ifg.pweb.entity;

import br.edu.ifg.pweb.dto.ProductDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "product")
@Table(name = "tb_products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


   private String ingredients;

    @ManyToMany(mappedBy = "products")
    private final List<Cart> carts = new ArrayList<>();;

    @Column(length = 399999999)
    private String imageName;

    public Product(){
    }

    public Product(Long id, String name, Double price, Category category, String ingredients, String imageName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.ingredients = ingredients;
        this.imageName = imageName;
    }

    public Product(ProductDTO productDTO){
        setId(productDTO.getId());
        setName(productDTO.getName());
        setPrice(productDTO.getPrice());
        setImageName(productDTO.getImageName());
        setIngredients(productDTO.getIngredients());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public List<Cart> getCharts() {
        return carts;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
