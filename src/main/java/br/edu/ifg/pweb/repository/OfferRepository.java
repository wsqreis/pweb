package br.edu.ifg.pweb.repository;

import br.edu.ifg.pweb.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    Offer findByName(String name);
}
