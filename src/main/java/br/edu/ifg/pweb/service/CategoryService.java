package br.edu.ifg.pweb.service;

import br.edu.ifg.pweb.dto.CategoryDTO;
import br.edu.ifg.pweb.entity.Category;
import br.edu.ifg.pweb.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LogService logService;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(UserDetails userDetails){
        List<Category> list = categoryRepository.findAll();
        logService.logAction("Searched all categories", userDetails.getUsername(), LocalDateTime.now());
        return list.stream()
                .map(x -> new CategoryDTO(x))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id, UserDetails userDetails){
        Optional<Category> obj = categoryRepository.findById(id);
        if (obj.isPresent()){
            Category entity = obj.get();
            logService.logAction("Searched category " + id, userDetails.getUsername(), LocalDateTime.now());
            return new CategoryDTO(entity);
        }else {
            return null;
        }
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto, UserDetails userDetails){
        Category entity = new Category(dto);
        entity = categoryRepository.save(entity);
        logService.logAction("Created new category", userDetails.getUsername(), LocalDateTime.now());

        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto, UserDetails userDetails) {
        Category entity = categoryRepository.findById(id).orElse(null);
        if (entity != null) {
            entity = categoryRepository.save(new Category(dto));
            logService.logAction("Updated category " + id, userDetails.getUsername(), LocalDateTime.now());
            return new CategoryDTO(entity);
        }else {
            return null;
        }
    }

    public boolean delete(Long id, UserDetails userDetails) {
        try {
            Optional<Category> obj = categoryRepository.findById(id);
            categoryRepository.deleteById(id);
            logService.logAction("Deleted category " + id, userDetails.getUsername(), LocalDateTime.now());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
