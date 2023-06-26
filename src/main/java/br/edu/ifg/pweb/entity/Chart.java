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

    @OneToOne(mappedBy = "chart")
    private User user;

    public Chart() {
    }

    public Chart(Long id, List<Product> products, User user) {
        this.id = id;
        this.products = products;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
