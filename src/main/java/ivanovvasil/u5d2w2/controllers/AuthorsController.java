package ivanovvasil.u5d2w2.controllers;

import ivanovvasil.u5d2w2.entities.Author;
import ivanovvasil.u5d2w2.services.AuthorsSevices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
  @Autowired
  private AuthorsSevices authorSevice;

  @GetMapping("")
  public Page<Author> getAll(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "15") int size,
                             @RequestParam(defaultValue = "name") String orderBy) {
    return authorSevice.findAll(page, size, orderBy);
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public Author saveAuthor(@RequestBody Author body) {
    return authorSevice.save(body);
  }

  @GetMapping("/{id}")
  public Author findById(@PathVariable int id) {
    return authorSevice.findById(id);
  }

  @PutMapping("/{id}")
  public Author findByIdAndUpdate(@PathVariable int id, @RequestBody Author body) {
    return authorSevice.findByIdAndUpdate(id, body);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void findByIdAndDelete(@PathVariable int id) {
    authorSevice.findByIdAndDelete(id);
  }
}
