package javatestweabapp.backendspringboot.controller;

import javatestweabapp.backendspringboot.entity.Stat;
import javatestweabapp.backendspringboot.repo.StatRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatController {

    private final StatRepository statRepository;

    public StatController(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    private final Long defaultId= 1L;

    @GetMapping("/stat")
    public ResponseEntity<Stat> findByFirstId(){
        return ResponseEntity.ok(statRepository.findById(defaultId).get());
    }
}
