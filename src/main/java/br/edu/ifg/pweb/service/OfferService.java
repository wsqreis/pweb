package br.edu.ifg.pweb.service;

import br.edu.ifg.pweb.dto.OfferDTO;
import br.edu.ifg.pweb.entity.Offer;
import br.edu.ifg.pweb.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private LogService logService;

    @Transactional(readOnly = true)
    public List<OfferDTO> findAll(UserDetails userDetails){
        List<Offer> list = offerRepository.findAll();
        logService.logAction("Searched all offers", userDetails.getUsername(), LocalDateTime.now());
        return list.stream()
                .map(x -> new OfferDTO(x))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OfferDTO findById(Long id, UserDetails userDetails){
        Optional<Offer> obj = offerRepository.findById(id);
        if (obj.isPresent()){
            Offer entity = obj.get();
            logService.logAction("Searched offer " + id, userDetails.getUsername(), LocalDateTime.now());
            return new OfferDTO(entity);
        }else {
            return null;
        }
    }

    @Transactional
    public OfferDTO insert(OfferDTO dto, UserDetails userDetails){
        Offer entity = new Offer(dto);
        entity = offerRepository.save(entity);
        logService.logAction("Created new offer", userDetails.getUsername(), LocalDateTime.now());
        return new OfferDTO(entity);
    }

    @Transactional
    public OfferDTO update(Long id, OfferDTO dto, UserDetails userDetails) {
        Offer entity = offerRepository.findById(id).orElse(null);
        if (entity != null) {
            entity = offerRepository.save(new Offer(dto));
            logService.logAction("Updated offer " + id, userDetails.getUsername(), LocalDateTime.now());
            return new OfferDTO(entity);
        }else {
            return null;
        }
    }

    public boolean delete(Long id, UserDetails userDetails) {
        try {
            offerRepository.deleteById(id);
            logService.logAction("Deleted offer " + id, userDetails.getUsername(), LocalDateTime.now());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
