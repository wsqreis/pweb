package br.edu.ifg.pweb.entity;

import br.edu.ifg.pweb.dto.ProductDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "product")
@Table(name = "tb_products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private final List<Ingredient> ingredients = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "chart_id")
    private Chart chart;

    private String imageName;

    public Product(){
    }

    public Product(Long id, String name, Double price, Category category, Chart chart) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.chart = chart;
    }

    public Product(ProductDTO productDTO){
        setId(productDTO.getId());
        setName(productDTO.getName());
        setPrice(productDTO.getPrice());
        setImageName(productDTO.getImageName());
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
