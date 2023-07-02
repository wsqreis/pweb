package br.edu.ifg.pweb.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;
    private String username;
    private LocalDateTime time;

    public Log() {
    }

    public Log(Long id, String action, String username, LocalDateTime time) {
        this.id = id;
        this.action = action;
        this.username = username;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
