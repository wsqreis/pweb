package br.edu.ifg.pweb.dto;

import br.edu.ifg.pweb.entity.Product;

public class ProductDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Double price;

    private String imageName;

    private String ingredients;
    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, Double price, String imageName, String ingredients) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageName = imageName;
        this.ingredients = ingredients;
    }

    public ProductDTO(Product product) {
        setId(product.getId());
        setName(product.getName());
        setPrice(product.getPrice());
        setImageName(product.getImageName());
        setIngredients(product.getIngredients());
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
