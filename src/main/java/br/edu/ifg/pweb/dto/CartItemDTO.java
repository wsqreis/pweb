package br.edu.ifg.pweb.dto;

import br.edu.ifg.pweb.entity.Offer;
import br.edu.ifg.pweb.entity.Product;

public class CartItemDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Double price;

    private String details;

    private String imageName;

    private String ingredients;

    public CartItemDTO() {
    }

    public CartItemDTO(Long id, String name, Double price, String details, String imageName, String ingredients) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.details = details;
        this.imageName = imageName;
        this.ingredients = ingredients;
    }

    public CartItemDTO(Product product){
        setId(product.getId());
        setName(product.getName());
        setPrice(product.getPrice());
        setImageName(product.getImageName());
        setIngredients(product.getIngredients());
    }

    public CartItemDTO(Offer offer){
        setId(offer.getId());
        setName(offer.getName());
        setPrice(offer.getPrice());
        setDetails(offer.getDetails());
        setImageName(offer.getImageName());
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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
