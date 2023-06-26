package br.edu.ifg.pweb.controller;

import br.edu.ifg.pweb.dto.IngredientDTO;
import br.edu.ifg.pweb.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/ingredients")
@CrossOrigin("*")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<IngredientDTO>> findAll(@AuthenticationPrincipal UserDetails userDetails){
        List<IngredientDTO> list = ingredientService.findAll(userDetails);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<IngredientDTO> findById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        IngredientDTO dto = ingredientService.findById(id, userDetails);
        if (dto != null) {
            return ResponseEntity.ok().body(dto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<IngredientDTO> insert(@RequestBody IngredientDTO dto, @AuthenticationPrincipal UserDetails userDetails){
        try {
            dto = ingredientService.insert(dto, userDetails);

            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(dto.getId()).toUri();

            return ResponseEntity.created(uri).body(dto);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<IngredientDTO> update(@PathVariable Long id, @RequestBody IngredientDTO dto, @AuthenticationPrincipal UserDetails userDetails){
        dto = ingredientService.update(id, dto, userDetails);
        if (dto != null) {
            return ResponseEntity.ok().body(dto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<IngredientDTO> delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        if (ingredientService.delete(id, userDetails)){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
