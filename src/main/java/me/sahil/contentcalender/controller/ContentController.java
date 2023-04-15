package me.sahil.contentcalender.controller;

import jakarta.validation.Valid;
import me.sahil.contentcalender.model.Content;
import me.sahil.contentcalender.model.Status;
import me.sahil.contentcalender.repository.ContentCollectionRepository;
import me.sahil.contentcalender.repository.ContentJdbcTemplateRepository;
import me.sahil.contentcalender.repository.ContentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@CrossOrigin(origins = "http://localhost:5500")
public class ContentController {
//    private final ContentCollectionRepository repository;
    private final ContentRepository repository;

    public ContentController(ContentRepository repository) {
        this.repository = repository;
    }

    // make a  request and final all the pieces of content in the system
    @GetMapping("")
    public List<Content> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById( @PathVariable Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found"));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void create( @Valid @RequestBody Content content) {
        repository.save(content);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @Valid @RequestBody Content content, @PathVariable Integer id) {
        if(!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
      @DeleteMapping("/{id}")
         public void delete(@PathVariable Integer id) {
            if(!repository.existsById(id)) {
               throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
            }
            repository.deleteById(id);
    }

    @GetMapping("/filter/{keyword}")
    public List<Content> findByTitle( @PathVariable  String keyword) {
        return repository.findAllByTitleContains(keyword);
    }

    @GetMapping("/filter/status/{status}")
    public List<Content> findByStatus( @PathVariable Status status) {
        return repository.listByStatus(status);
    }

}
