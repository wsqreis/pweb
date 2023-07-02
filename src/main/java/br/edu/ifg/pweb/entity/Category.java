package br.edu.ifg.pweb.entity;

import br.edu.ifg.pweb.dto.CategoryDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(length = 399999999, unique = true)
    private String imageName;

    @OneToMany(mappedBy = "category")
    private final List<Product> products = new ArrayList<>();

    public Category() {
    }

    public Category(Long id, String name, String imageName) {
        this.id = id;
        this.name = name;
        this.imageName = imageName;
    }

    public Category(CategoryDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.imageName = dto.getImageName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public List<Product> getProducts() {
        return products;
    }
}
