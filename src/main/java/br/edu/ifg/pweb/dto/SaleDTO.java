package br.edu.ifg.pweb.dto;

import br.edu.ifg.pweb.entity.Sale;

public class SaleDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private double total;

    public SaleDTO() {
    }

    public SaleDTO(Long id, String username, double total) {
        this.id = id;
        this.username = username;
        this.total = total;
    }

    public SaleDTO(Sale sale){
        setId(sale.getId());
        setUsername(sale.getUsername());
        setTotal(sale.getTotal());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
