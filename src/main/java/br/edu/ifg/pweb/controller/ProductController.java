package br.edu.ifg.pweb.controller;

import br.edu.ifg.pweb.dto.ProductDTO;
import br.edu.ifg.pweb.service.ProductService;
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
@RequestMapping(value = "/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/name/{name}")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<List<ProductDTO>> findByCategory(@PathVariable String name, @AuthenticationPrincipal UserDetails userDetails) {
        List<ProductDTO> list = productService.findByCategory(name, userDetails);
        if (list != null) {
            return ResponseEntity.ok().body(list);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/name/{name}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<ProductDTO> insert(@PathVariable String name, @RequestBody ProductDTO dto, @AuthenticationPrincipal UserDetails userDetails) {
        dto = productService.insert(dto, name, userDetails);
        if (dto != null) {
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(dto.getId()).toUri();

            return ResponseEntity.created(uri).body(dto);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto, @AuthenticationPrincipal UserDetails userDetails){
        dto = productService.update(id, dto, userDetails);
        if (dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        ProductDTO dto = productService.findById(id, userDetails);
        if (dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<ProductDTO> delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        if (productService.delete(id, userDetails)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
