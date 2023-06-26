package br.edu.ifg.pweb.dto;

import br.edu.ifg.pweb.entity.User;

public class UserDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;
    private String login;

    private String password;

    private String role;

    public UserDTO() {
    }

    public UserDTO(Long id, String login, String password, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public UserDTO(User user){
        setLogin(user.getUsername());
        setRole(user.getRole());
        setName(user.getName());
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
