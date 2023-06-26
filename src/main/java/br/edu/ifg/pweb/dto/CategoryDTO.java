package br.edu.ifg.pweb.dto;

import br.edu.ifg.pweb.entity.Category;

public class CategoryDTO {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    private String imageName;

    public CategoryDTO(){
    }

    public CategoryDTO(Long id, String name, String imageName) {
        this.id = id;
        this.name = name;
        this.imageName = imageName;
    }

    public CategoryDTO(Category entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.imageName = entity.getImageName();
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
}
