package br.edu.ifg.pweb.service;

import br.edu.ifg.pweb.dto.IngredientDTO;
import br.edu.ifg.pweb.entity.Ingredient;
import br.edu.ifg.pweb.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private LogService logService;

    @Transactional(readOnly = true)
    public List<IngredientDTO> findAll(UserDetails userDetails){
        List<Ingredient> list = ingredientRepository.findAll();
        logService.logAction("Searched all ingredients", userDetails.getUsername(), LocalDateTime.now());
        return list.stream()
                .map(x -> new IngredientDTO(x))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public IngredientDTO findById(Long id, UserDetails userDetails){
        Optional<Ingredient> obj = ingredientRepository.findById(id);
        if (obj.isPresent()){
            Ingredient entity = obj.get();
            logService.logAction("Searched ingredient " + id, userDetails.getUsername(), LocalDateTime.now());
            return new IngredientDTO(entity);
        }else {
            return null;
        }
    }

    @Transactional
    public IngredientDTO insert(IngredientDTO dto, UserDetails userDetails){
        Ingredient entity = new Ingredient(dto);
        entity = ingredientRepository.save(entity);
        logService.logAction("Created new ingredient", userDetails.getUsername(), LocalDateTime.now());
        return new IngredientDTO(entity);
    }

    @Transactional
    public IngredientDTO update(Long id, IngredientDTO dto, UserDetails userDetails) {
        Ingredient entity = ingredientRepository.findById(id).orElse(null);
        if (entity != null) {
            entity = ingredientRepository.save(new Ingredient(dto));
            logService.logAction("Updated ingredient " + id, userDetails.getUsername(), LocalDateTime.now());
            return new IngredientDTO(entity);
        }else {
            return null;
        }
    }

    public boolean delete(Long id, UserDetails userDetails) {
        try {
            ingredientRepository.deleteById(id);
            logService.logAction("Deleted ingredient " + id, userDetails.getUsername(), LocalDateTime.now());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
