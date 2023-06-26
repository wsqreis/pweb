package br.edu.ifg.pweb.controller;

import br.edu.ifg.pweb.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/{image}")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable String image, @AuthenticationPrincipal UserDetails userDetails){
        byte[] file = imageService.getImage(image, userDetails);
        if (file != null) {
            return ResponseEntity.ok().body(file);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/{entity}/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<byte[]> insert(@PathVariable String entity, @PathVariable Long id, @RequestParam("file") MultipartFile file, @AuthenticationPrincipal UserDetails userDetails){
        try {
            if (imageService.addImage(file, entity, id, userDetails)) {
                byte[] image = imageService.getImage(file.getOriginalFilename(), userDetails);

                URI uri = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{name}")
                        .buildAndExpand(file.getOriginalFilename()).toUri();

                return ResponseEntity.created(uri).body(image);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
