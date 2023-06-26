package br.edu.ifg.pweb.controller;

import br.edu.ifg.pweb.dto.CategoryDTO;
import br.edu.ifg.pweb.service.CategoryService;
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
@RequestMapping(value = "/categories")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<List<CategoryDTO>> findAll(@AuthenticationPrincipal UserDetails userDetails){
        List<CategoryDTO> list = categoryService.findAll(userDetails);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        CategoryDTO dto = categoryService.findById(id, userDetails);
        if (dto != null) {
            return ResponseEntity.ok().body(dto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto, @AuthenticationPrincipal UserDetails userDetails){
        try {
            dto = categoryService.insert(dto, userDetails);

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
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO dto, @AuthenticationPrincipal UserDetails userDetails){
        dto = categoryService.update(id, dto, userDetails);
        if (dto != null) {
            return ResponseEntity.ok().body(dto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<CategoryDTO> delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        if (categoryService.delete(id, userDetails)){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }


}
