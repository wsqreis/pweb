package br.edu.ifg.pweb.service;

import br.edu.ifg.pweb.dto.*;
import br.edu.ifg.pweb.entity.*;
import br.edu.ifg.pweb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChartService {

    @Autowired
    private ChartRepository chartRepository;

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
    public ChartDTO getChart(UserDetails userDetails){
        User user = userRepository.findByLogin(userDetails.getUsername());
        Chart entity = user.getChart();
        return new ChartDTO(entity);
    }

    @Transactional
    public ChartDTO insertProduct(ProductDTO dto, UserDetails userDetails){
        User user = userRepository.findByLogin(userDetails.getUsername());
        Chart entity = user.getChart();
        entity.getProducts().add(new Product(dto));
        entity = chartRepository.save(entity);
        logService.logAction("Added " + dto.getName() + " to chart", userDetails.getUsername(), LocalDateTime.now());
        return new ChartDTO(entity);
    }

    public boolean removeProduct(Long id, UserDetails userDetails) {
        Optional<Product> obj = productRepository.findById(id);
        if (obj.isPresent()) {
            Product product = obj.get();
            User user = userRepository.findByLogin(userDetails.getUsername());
            Chart entity = user.getChart();
            entity.getProducts().remove(product);
            entity = chartRepository.save(entity);
            logService.logAction("Removed " + product.getName() + " from chart", userDetails.getUsername(), LocalDateTime.now());
            return true;
        }else {
            return false;
        }
    }

    @Transactional
    public ChartDTO insertOffer(OfferDTO dto, UserDetails userDetails){
        User user = userRepository.findByLogin(userDetails.getUsername());
        Chart entity = user.getChart();
        entity.getOffers().add(new Offer(dto));
        entity = chartRepository.save(entity);
        logService.logAction("Added " + dto.getName() + " to chart", userDetails.getUsername(), LocalDateTime.now());
        return new ChartDTO(entity);
    }

    public boolean removeOffer(Long id, UserDetails userDetails) {
        Optional<Offer> obj = offerRepository.findById(id);
        if (obj.isPresent()) {
            Offer offer = obj.get();
            User user = userRepository.findByLogin(userDetails.getUsername());
            Chart entity = user.getChart();
            entity.getOffers().remove(offer);
            entity = chartRepository.save(entity);
            logService.logAction("Removed " + offer.getName() + " from chart", userDetails.getUsername(), LocalDateTime.now());
            return true;
        }else {
            return false;
        }
    }

    public ChartDTO checkout(UserDetails userDetails){
        User user = userRepository.findByLogin(userDetails.getUsername());
        Chart entity = user.getChart();
        ChartDTO dto = new ChartDTO(entity);

        Sale sale = new Sale();
        sale.setUsername(user.getUsername());
        sale.setTotal(dto.getTotal());
        sale = saleRepository.save(sale);

        entity.getProducts().removeAll(entity.getProducts());
        entity.getOffers().removeAll(entity.getOffers());
        entity = chartRepository.save(entity);

        return new ChartDTO(entity);
    }
}
