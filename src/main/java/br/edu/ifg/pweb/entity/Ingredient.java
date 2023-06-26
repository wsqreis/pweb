package br.edu.ifg.pweb.entity;

import br.edu.ifg.pweb.dto.IngredientDTO;

import javax.persistence.*;

@Entity
@Table(name = "tb_ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private Double price;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Ingredient() {
    }

    public Ingredient(Long id, String name, Double price, Product product) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.product = product;
    }

    public Ingredient(IngredientDTO ingredientDTO){
        setId(ingredientDTO.getId());
        setName(ingredientDTO.getName());
        setPrice(ingredientDTO.getPrice());
        setProduct(ingredientDTO.getProduct());
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
