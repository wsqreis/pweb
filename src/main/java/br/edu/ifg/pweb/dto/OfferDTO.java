package br.edu.ifg.pweb.dto;

import br.edu.ifg.pweb.entity.Offer;

public class OfferDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Double price;

    private String details;

    private String imageName;

    public OfferDTO() {
    }

    public OfferDTO(Long id, String name, Double price, String details, String imageName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.details = details;
        this.imageName = imageName;
    }

    public OfferDTO(Offer offer){
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
}
