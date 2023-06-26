package br.edu.ifg.pweb.dto;

import br.edu.ifg.pweb.entity.Chart;
import br.edu.ifg.pweb.entity.Product;

import java.util.List;

public class ChartDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private List<Product> products;

    public ChartDTO() {
    }

    public ChartDTO(Long id, List<Product> products) {
        this.id = id;
        this.products = products;
    }

    public ChartDTO(Chart chart){
        setId(chart.getId());
        setProducts(chart.getProducts());
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

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
