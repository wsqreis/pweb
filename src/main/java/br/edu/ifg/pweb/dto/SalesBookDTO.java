package br.edu.ifg.pweb.dto;

import br.edu.ifg.pweb.entity.Sale;

import java.util.ArrayList;
import java.util.List;

public class SalesBookDTO {

    private final List<SaleDTO> sales = new ArrayList<>();

    private double total = 0.0;

    public SalesBookDTO() {
    }

    public SalesBookDTO(List<Sale> sales){
        for (Sale sale : sales){
            this.sales.add(new SaleDTO(sale));
            total += sale.getTotal();
        }
    }

    public List<SaleDTO> getSales() {
        return sales;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
