package br.edu.ifg.pweb.service;

import br.edu.ifg.pweb.dto.*;
import br.edu.ifg.pweb.entity.*;
import br.edu.ifg.pweb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private LogService logService;

    @Transactional(readOnly = true)
    public CartDTO getChart(UserDetails userDetails){
        User user = userRepository.findByLogin(userDetails.getUsername());
        Cart entity = user.getChart();

        logService.logAction("Visited cart", userDetails.getUsername(), LocalDateTime.now());
        return new CartDTO(entity);
    }

    @Transactional
    public CartDTO insertProduct(ProductDTO dto, UserDetails userDetails){
        User user = userRepository.findByLogin(userDetails.getUsername());

        Cart cart = user.getChart();
        Product product = new Product(dto);

        cart.getProducts().add(product);
        product.getCharts().add(cart);

        cart = cartRepository.save(cart);
        product = productRepository.save(product);

        logService.logAction("Added " + dto.getName() + " to cart", userDetails.getUsername(), LocalDateTime.now());
        return new CartDTO(cart);
    }

    public boolean removeProduct(Long id, UserDetails userDetails) {
        Optional<Product> obj = productRepository.findById(id);
        if (obj.isPresent()) {
            User user = userRepository.findByLogin(userDetails.getUsername());

            Cart cart = user.getChart();
            Product product = obj.get();

            cart.getProducts().remove(product);
            product.getCharts().remove(cart);

            cart = cartRepository.save(cart);
            product = productRepository.save(product);

            logService.logAction("Removed " + product.getName() + " from cart", userDetails.getUsername(), LocalDateTime.now());
            return true;
        }else {
            return false;
        }
    }

    @Transactional
    public CartDTO insertOffer(OfferDTO dto, UserDetails userDetails){
        User user = userRepository.findByLogin(userDetails.getUsername());

        Cart cart = user.getChart();
        Offer offer = new Offer(dto);

        cart.getOffers().add(offer);
        offer.getCharts().add(cart);

        cart = cartRepository.save(cart);
        offer = offerRepository.save(offer);

        logService.logAction("Added " + dto.getName() + " to cart", userDetails.getUsername(), LocalDateTime.now());
        return new CartDTO(cart);
    }

    public boolean removeOffer(Long id, UserDetails userDetails) {
        Optional<Offer> obj = offerRepository.findById(id);
        if (obj.isPresent()) {
            User user = userRepository.findByLogin(userDetails.getUsername());

            Cart cart = user.getChart();
            Offer offer = obj.get();

            cart.getOffers().remove(offer);
            offer.getCharts().remove(cart);

            cart = cartRepository.save(cart);
            offer = offerRepository.save(offer);

            logService.logAction("Removed " + offer.getName() + " from cart", userDetails.getUsername(), LocalDateTime.now());
            return true;
        }else {
            return false;
        }
    }

    public CartDTO checkout(UserDetails userDetails){
        User user = userRepository.findByLogin(userDetails.getUsername());
        Cart cart = user.getChart();
        CartDTO dto = new CartDTO(cart);

        Sale sale = new Sale();
        sale.setUsername(user.getUsername());
        sale.setTotal(dto.getTotal());
        sale = saleRepository.save(sale);

        cart.getProducts().removeAll(cart.getProducts());
        cart.getOffers().removeAll(cart.getOffers());
        cart = cartRepository.save(cart);

        logService.logAction("Bought cart items", userDetails.getUsername(), LocalDateTime.now());
        return new CartDTO(cart);
    }
}
