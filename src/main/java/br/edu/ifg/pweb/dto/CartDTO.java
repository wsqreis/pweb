package br.edu.ifg.pweb.dto;

import br.edu.ifg.pweb.entity.Cart;
import br.edu.ifg.pweb.entity.Offer;
import br.edu.ifg.pweb.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class CartDTO {
    private static final long serialVersionUID = 1L;

    private List<CartItemDTO> list = new ArrayList<>();

    private double total = 0.0;

    public CartDTO() {
    }

    public CartDTO(List<CartItemDTO> list, double total) {
        this.list = list;
        this.total = total;
    }

    public CartDTO(Cart cart){

        for (Product product : cart.getProducts()) {
            total += product.getPrice();
            list.add(new CartItemDTO(product));
        }

        for (Offer offer : cart.getOffers()) {
            total += offer.getPrice();
            list.add(new CartItemDTO(offer));
        }
    }

    public List<CartItemDTO> getList() {
        return list;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
