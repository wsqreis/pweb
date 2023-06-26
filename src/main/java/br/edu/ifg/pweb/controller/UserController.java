package br.edu.ifg.pweb.controller;

import br.edu.ifg.pweb.dto.UserDTO;
import br.edu.ifg.pweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/info")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<UserDTO> getLoggedUser(@AuthenticationPrincipal UserDetails userDetails){
        UserDTO dto = userService.getLoggedUser(userDetails);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserDTO> insertUser(@RequestBody UserDTO dto){
        try {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
            dto = userService.insertUser(dto);

            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(dto.getId()).toUri();

            return ResponseEntity.created(uri).body(dto);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/admin")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<UserDTO> insertAdmin(@RequestBody UserDTO dto, @AuthenticationPrincipal UserDetails userDetails){
        try {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
            dto = userService.insertAdmin(dto, userDetails);

            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(dto.getId()).toUri();

            return ResponseEntity.created(uri).body(dto);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
