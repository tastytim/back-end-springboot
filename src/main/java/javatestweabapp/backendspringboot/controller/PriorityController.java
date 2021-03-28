package javatestweabapp.backendspringboot.controller;

import javatestweabapp.backendspringboot.entity.Priority;
import javatestweabapp.backendspringboot.repo.PriorityRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private PriorityRepository priorityRepository;

    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @GetMapping("/all")
    public List<Priority> findAllByOrderByIdAsc() {
        List<Priority> all = priorityRepository.findAllByOrderByIdAsc();
        return all;
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody Priority priority) {
        if (priority.getId() != null && priority.getId() != 0) {
            return new ResponseEntity("Id must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priority.getTitle() == null && priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("Title missed", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priority.getColor() == null && priority.getColor().trim().length() == 0) {
            return new ResponseEntity("Color missed", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityRepository.save(priority));
    }


    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Priority priority) {
        if (priority.getId() == null && priority.getId() == 0) {
            return new ResponseEntity("Id must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priority.getTitle() == null && priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("Title missed", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priority.getColor() == null && priority.getColor().trim().length() == 0) {
            return new ResponseEntity("Color missed", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(priorityRepository.save(priority));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        try {
            priorityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("This id not exist", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Priority> findById(@PathVariable Long id) {
        Priority priority = null;
        try{
            priority = priorityRepository.findById(id).get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(priority);
    }
}
