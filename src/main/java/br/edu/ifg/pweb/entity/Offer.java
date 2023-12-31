package br.edu.ifg.pweb.entity;

import br.edu.ifg.pweb.dto.OfferDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private String details;
    @Column(length = 399999999)
    private String imageName;

    @ManyToMany(mappedBy = "offers")
    private final List<Cart> carts = new ArrayList<>();

    public Offer() {
    }

    public Offer(Long id, String name, Double price, String details, String imageName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.details = details;
        this.imageName = imageName;
    }

    public Offer(OfferDTO offerDTO){
        setId(offerDTO.getId());
        setName(offerDTO.getName());
        setPrice(offerDTO.getPrice());
        setDetails(offerDTO.getDetails());
        setImageName(offerDTO.getImageName());
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

    public List<Cart> getCharts() {
        return carts;
    }
}
