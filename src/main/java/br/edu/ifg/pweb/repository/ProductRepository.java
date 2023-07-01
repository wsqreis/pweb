package br.edu.ifg.pweb.repository;

import br.edu.ifg.pweb.entity.Category;
import br.edu.ifg.pweb.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(Category category);
}
