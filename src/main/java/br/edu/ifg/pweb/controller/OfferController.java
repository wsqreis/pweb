package br.edu.ifg.pweb.controller;

import br.edu.ifg.pweb.dto.OfferDTO;
import br.edu.ifg.pweb.service.OfferService;
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
@RequestMapping(value = "/offers")
@CrossOrigin("*")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @GetMapping
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<List<OfferDTO>> findAll(@AuthenticationPrincipal UserDetails userDetails){
        List<OfferDTO> list = offerService.findAll(userDetails);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<OfferDTO> findById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        OfferDTO dto = offerService.findById(id, userDetails);
        if (dto != null) {
            return ResponseEntity.ok().body(dto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<OfferDTO> insert(@RequestBody OfferDTO dto, @AuthenticationPrincipal UserDetails userDetails){
        try {
            dto = offerService.insert(dto, userDetails);

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
    public ResponseEntity<OfferDTO> update(@PathVariable Long id, @RequestBody OfferDTO dto, @AuthenticationPrincipal UserDetails userDetails){
        dto = offerService.update(id, dto, userDetails);
        if (dto != null) {
            return ResponseEntity.ok().body(dto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<OfferDTO> delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        if (offerService.delete(id, userDetails)){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
