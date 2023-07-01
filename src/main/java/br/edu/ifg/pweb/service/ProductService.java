package br.edu.ifg.pweb.service;

import br.edu.ifg.pweb.dto.ProductDTO;
import br.edu.ifg.pweb.entity.Category;
import br.edu.ifg.pweb.entity.Product;
import br.edu.ifg.pweb.repository.CategoryRepository;
import br.edu.ifg.pweb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LogService logService;

    @Transactional(readOnly = true)
    public List<ProductDTO> findByCategory(String name, UserDetails userDetails) {
        try {
            Category category = categoryRepository.findByName(name);
            List<Product> list = productRepository.findByCategory(category);
            logService.logAction("Searched products of category " + name, userDetails.getUsername(), LocalDateTime.now());
            return list.stream()
                    .map(x -> new ProductDTO(x))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id, UserDetails userDetails) {
        Optional<Product> obj = productRepository.findById(id);
        if (obj.isPresent()) {
            Product entity = obj.get();
            logService.logAction("Searched product " + id, userDetails.getUsername(), LocalDateTime.now());
            return new ProductDTO(entity);
        } else {
            return null;
        }
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto, String name, UserDetails userDetails) {
        try {
            Product entity = new Product(dto);
            Category category = categoryRepository.findByName(name);
            entity.setCategory(category);
            entity = productRepository.save(entity);
            logService.logAction("Created new product", userDetails.getUsername(), LocalDateTime.now());
            return new ProductDTO(entity);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto, UserDetails userDetails) {
        Product entity = productRepository.findById(id).orElse(null);
        if (entity != null) {
            Product toSave = new Product(dto);
            toSave.setCategory(entity.getCategory());
            entity = productRepository.save(toSave);
            logService.logAction("Updated product " + id, userDetails.getUsername(), LocalDateTime.now());
            return new ProductDTO(entity);
        }else {
            return null;
        }
    }

    public boolean delete(Long id, UserDetails userDetails) {
        try {
            productRepository.deleteById(id);
            logService.logAction("Deleted product " + id, userDetails.getUsername(), LocalDateTime.now());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
