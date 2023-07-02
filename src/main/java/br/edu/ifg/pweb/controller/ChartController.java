package br.edu.ifg.pweb.controller;

import br.edu.ifg.pweb.dto.CategoryDTO;
import br.edu.ifg.pweb.dto.ChartDTO;
import br.edu.ifg.pweb.dto.OfferDTO;
import br.edu.ifg.pweb.dto.ProductDTO;
import br.edu.ifg.pweb.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/chart")
@CrossOrigin("*")
public class ChartController {

    @Autowired
    private ChartService chartService;

    @GetMapping
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<ChartDTO> getChart(@AuthenticationPrincipal UserDetails userDetails){
        ChartDTO dto = chartService.getChart(userDetails);
        if (dto != null) {
            return ResponseEntity.ok().body(dto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/product")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<ChartDTO> insertProduct(@RequestBody ProductDTO productDTO, @AuthenticationPrincipal UserDetails userDetails){
        try {
            ChartDTO dto = chartService.insertProduct(productDTO, userDetails);

            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(dto.getId()).toUri();

            return ResponseEntity.created(uri).body(dto);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/product/{id}")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<String> removeProduct(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        if (chartService.removeProduct(id, userDetails)){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/offer")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<ChartDTO> insertOffer(@RequestBody OfferDTO offerDTO, @AuthenticationPrincipal UserDetails userDetails){
        try {
            ChartDTO dto = chartService.insertOffer(offerDTO, userDetails);

            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(dto.getId()).toUri();

            return ResponseEntity.created(uri).body(dto);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/offer/{id}")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<String> removeOffer(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        if (chartService.removeOffer(id, userDetails)){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/checkout")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<ChartDTO> checkout(@AuthenticationPrincipal UserDetails userDetails){
        ChartDTO dto = chartService.checkout(userDetails);
        if (dto != null) {
            return ResponseEntity.ok().body(dto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
