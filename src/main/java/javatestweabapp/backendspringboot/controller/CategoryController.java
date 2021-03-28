package javatestweabapp.backendspringboot.controller;

import javatestweabapp.backendspringboot.entity.Category;
import javatestweabapp.backendspringboot.repo.CategoryRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/all")
    public List<Category> findAllByOrderByIdTitleAsc() {
        return categoryRepository.findAllByOrderByTitleAsc();
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody Category category) {
        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("Id must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (category.getTitle() == null && category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param:Title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Category category) {
        if (category.getId() == null && category.getId() == 0) {
            return new ResponseEntity("Id must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (category.getTitle() == null && category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param:Title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        try{
            categoryRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("This id not exist", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category category = null;
        try{
            category = categoryRepository.findById(id).get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(category);
    }
}
