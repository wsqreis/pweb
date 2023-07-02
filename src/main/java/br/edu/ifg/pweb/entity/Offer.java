package br.edu.ifg.pweb.entity;

import br.edu.ifg.pweb.dto.OfferDTO;

import javax.persistence.*;

@Entity
@Table(name = "tb_offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private Double price;

    private String details;
    @Column(length = 399999999)
    private String imageName;

    @ManyToOne
    @JoinColumn(name = "chart_id")
    private Chart chart;

    public Offer() {
    }

    public Offer(Long id, String name, Double price, String details, String imageName, Chart chart) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.details = details;
        this.imageName = imageName;
        this.chart = chart;
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

    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }
}
