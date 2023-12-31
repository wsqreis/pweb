package br.edu.ifg.pweb.service;

import br.edu.ifg.pweb.dto.UserDTO;
import br.edu.ifg.pweb.entity.Cart;
import br.edu.ifg.pweb.entity.User;
import br.edu.ifg.pweb.repository.CartRepository;
import br.edu.ifg.pweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private LogService logService;

    @Transactional(readOnly = true)
    public UserDTO getLoggedUser(UserDetails userDetails){
        User user = userRepository.findByLogin(userDetails.getUsername());
        logService.logAction("Checked user information", user.getUsername(), LocalDateTime.now());
        return new UserDTO(user);
    }
    public boolean userExists(UserDTO userDTO){
        try {
            User user = userRepository.findByLogin(userDTO.getLogin());
            return user != null;
        }catch (Exception e){
            return false;
        }
    }

    @Transactional
    public UserDTO insertUser(UserDTO dto){
        User entity = new User(dto);
        entity.setRole("user");
        Cart cart = new Cart();
        cart = cartRepository.save(cart);
        entity.setChart(cart);
        entity = userRepository.save(entity);
        logService.logAction("User registered", entity.getUsername(), LocalDateTime.now());

        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO insertAdmin(UserDTO dto, UserDetails userDetails){
        User entity = new User(dto);
        entity.setRole("admin");
        Cart cart = new Cart();
        cart = cartRepository.save(cart);
        entity.setChart(cart);
        entity = userRepository.save(entity);
        logService.logAction("Admin " + entity.getUsername() + " registered", userDetails.getUsername(), LocalDateTime.now());

        return new UserDTO(entity);
    }
}
