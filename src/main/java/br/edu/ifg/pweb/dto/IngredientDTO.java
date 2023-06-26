package br.edu.ifg.pweb.dto;

import br.edu.ifg.pweb.entity.Ingredient;
import br.edu.ifg.pweb.entity.Product;

public class IngredientDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Double price;

    private Product product;

    public IngredientDTO() {
    }

    public IngredientDTO(Long id, String name, Double price, Product product) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.product = product;
    }

    public IngredientDTO(Ingredient ingredient){
        setId(ingredient.getId());
        setName(ingredient.getName());
        setPrice(ingredient.getPrice());
        setProduct(ingredient.getProduct());
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
