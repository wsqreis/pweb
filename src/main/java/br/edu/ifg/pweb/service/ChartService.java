package br.edu.ifg.pweb.service;

import br.edu.ifg.pweb.dto.ChartDTO;
import br.edu.ifg.pweb.dto.ProductDTO;
import br.edu.ifg.pweb.entity.Chart;
import br.edu.ifg.pweb.entity.Product;
import br.edu.ifg.pweb.entity.User;
import br.edu.ifg.pweb.repository.ChartRepository;
import br.edu.ifg.pweb.repository.ProductRepository;
import br.edu.ifg.pweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ChartService {

    @Autowired
    private ChartRepository chartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

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
}
