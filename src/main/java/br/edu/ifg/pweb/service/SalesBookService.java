package br.edu.ifg.pweb.service;

import br.edu.ifg.pweb.dto.SaleDTO;
import br.edu.ifg.pweb.dto.SalesBookDTO;
import br.edu.ifg.pweb.entity.Sale;
import br.edu.ifg.pweb.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SalesBookService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private LogService logService;

    @Transactional(readOnly = true)
    public SalesBookDTO getSalesBook(UserDetails userDetails){
        List<Sale> list = saleRepository.findAll();
        logService.logAction("Visited sales book", userDetails.getUsername(), LocalDateTime.now());
        return new SalesBookDTO(list);
    }
}
