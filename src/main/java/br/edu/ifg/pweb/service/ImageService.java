package br.edu.ifg.pweb.service;

import br.edu.ifg.pweb.entity.Category;
import br.edu.ifg.pweb.entity.Offer;
import br.edu.ifg.pweb.entity.Product;
import br.edu.ifg.pweb.repository.CategoryRepository;
import br.edu.ifg.pweb.repository.OfferRepository;
import br.edu.ifg.pweb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class ImageService {

    @Autowired
    private LogService logService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OfferRepository offerRepository;

    private static final String directory = System.getProperty("user.dir");

    private static final String imagesPath = directory + "\\src\\main\\resources\\images\\";

    public boolean addImage(MultipartFile file, String entity, Long id, UserDetails userDetails){
        try{
            if (!file.isEmpty() && Objects.requireNonNull(file.getContentType()).startsWith("image/")){

                boolean entityMatches = false;

                if (entity.equals("category")){
                    Category category = categoryRepository.findById(id).orElse(null);
                    if (category != null){
                        category.setImageName(file.getOriginalFilename());
                        categoryRepository.save(category);
                        entityMatches = true;
                    }
                }

                if (entity.equals("product")){
                    Product product = productRepository.findById(id).orElse(null);
                    if (product != null){
                        product.setImageName(file.getOriginalFilename());
                        productRepository.save(product);
                        entityMatches = true;
                    }
                }

                if (entity.equals("offer")){
                    Offer offer = offerRepository.findById(id).orElse(null);
                    if (offer != null){
                        offer.setImageName(file.getOriginalFilename());
                        offerRepository.save(offer);
                        entityMatches = true;
                    }
                }

                if (entityMatches) {
                    byte[] bytes = file.getBytes();
                    Path path = Paths.get(imagesPath + file.getOriginalFilename());
                    Files.write(path, bytes);
                    logService.logAction("Added image " + file.getOriginalFilename(), userDetails.getUsername(), LocalDateTime.now());
                }

                return entityMatches;
            }
        }catch (IOException e){
            return false;
        }
        return false;
    }

    public boolean removeImage(String name, UserDetails userDetails){
        try{
            Path path = Paths.get(imagesPath + name);
            Files.delete(path);
            logService.logAction("Deleted image " + name, userDetails.getUsername(), LocalDateTime.now());
            return true;
        }catch (IOException e){
            return false;
        }
    }

    public byte[] getImage(String imageName, UserDetails userDetails) {
        try {
            File image = new File(imagesPath + imageName);
            logService.logAction("Requested image " + imageName, userDetails.getUsername(), LocalDateTime.now());
            return Files.readAllBytes(image.toPath());
        }catch (IOException e){
            return null;
        }
    }
}
