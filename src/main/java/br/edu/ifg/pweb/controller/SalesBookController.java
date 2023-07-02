package br.edu.ifg.pweb.controller;

import br.edu.ifg.pweb.dto.OfferDTO;
import br.edu.ifg.pweb.dto.SalesBookDTO;
import br.edu.ifg.pweb.service.SalesBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sales")
@CrossOrigin("*")
public class SalesBookController {

    @Autowired
    private SalesBookService salesBookService;

    @GetMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<SalesBookDTO> findAll(@AuthenticationPrincipal UserDetails userDetails){
        SalesBookDTO dto = salesBookService.getSalesBook(userDetails);
        return ResponseEntity.ok().body(dto);
    }
}
