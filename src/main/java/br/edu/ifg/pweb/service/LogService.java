package br.edu.ifg.pweb.service;

import br.edu.ifg.pweb.entity.Log;
import br.edu.ifg.pweb.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogService {
    @Autowired
    private  LogRepository logRepository;

    public void logAction(String action, String username, LocalDateTime time) {
        Log log = new Log();
        log.setAction(action);
        log.setUsername(username);
        log.setTime(time);

        logRepository.save(log);
    }
}
