package br.edu.ifg.pweb.controller;

import br.edu.ifg.pweb.dto.CartDTO;
import br.edu.ifg.pweb.dto.OfferDTO;
import br.edu.ifg.pweb.dto.ProductDTO;
import br.edu.ifg.pweb.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/cart")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<CartDTO> getChart(@AuthenticationPrincipal UserDetails userDetails){
        CartDTO dto = cartService.getChart(userDetails);
        if (dto != null) {
            return ResponseEntity.ok().body(dto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/product")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<CartDTO> insertProduct(@RequestBody ProductDTO productDTO, @AuthenticationPrincipal UserDetails userDetails){
        try {
            CartDTO dto = cartService.insertProduct(productDTO, userDetails);

            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(productDTO.getId()).toUri();

            return ResponseEntity.created(uri).body(dto);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/product/{id}")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<String> removeProduct(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        if (cartService.removeProduct(id, userDetails)){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/offer")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<CartDTO> insertOffer(@RequestBody OfferDTO offerDTO, @AuthenticationPrincipal UserDetails userDetails){
        try {
            CartDTO dto = cartService.insertOffer(offerDTO, userDetails);

            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(offerDTO.getId()).toUri();

            return ResponseEntity.created(uri).body(dto);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/offer/{id}")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<String> removeOffer(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        if (cartService.removeOffer(id, userDetails)){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/checkout")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<CartDTO> checkout(@AuthenticationPrincipal UserDetails userDetails){
        CartDTO dto = cartService.checkout(userDetails);
        if (dto != null) {
            return ResponseEntity.ok().body(dto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
