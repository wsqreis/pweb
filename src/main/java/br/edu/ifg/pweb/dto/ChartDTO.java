package br.edu.ifg.pweb.dto;

import br.edu.ifg.pweb.entity.Chart;
import br.edu.ifg.pweb.entity.Offer;
import br.edu.ifg.pweb.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ChartDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private List<ChartItemDTO> list = new ArrayList<>();

    private double total = 0.0;

    public ChartDTO() {
    }

    public ChartDTO(Long id, List<ChartItemDTO> list, double total) {
        this.id = id;
        this.list = list;
        this.total = total;
    }

    public ChartDTO(Chart chart){
        setId(chart.getId());

        for (Product product : chart.getProducts()) {
            total += product.getPrice();
            list.add(new ChartItemDTO(product));
        }

        for (Offer offer : chart.getOffers()) {
            total += offer.getPrice();
            list.add(new ChartItemDTO(offer));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ChartItemDTO> getList() {
        return list;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
