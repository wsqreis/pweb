package br.edu.ifg.pweb.entity;

import br.edu.ifg.pweb.dto.ChartDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_charts")
public class Chart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(mappedBy = "chart")
    private List<Product> products;

    @OneToMany(mappedBy = "chart")
    private List<Offer> offers;

    @OneToOne(mappedBy = "chart")
    private User user;

    public Chart() {
    }

    public Chart(Long id, List<Product> products, List<Offer> offers, User user) {
        this.id = id;
        this.products = products;
        this.offers = offers;
        this.user = user;
    }

    public Chart(ChartDTO chartDTO){
        setId(chartDTO.getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
