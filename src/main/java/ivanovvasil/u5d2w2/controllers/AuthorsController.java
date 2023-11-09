package ivanovvasil.u5d2w2.controllers;

import ivanovvasil.u5d2w2.entities.Author;
import ivanovvasil.u5d2w2.exceptions.BadRequestException;
import ivanovvasil.u5d2w2.payloads.authors.NewAuthorDTO;
import ivanovvasil.u5d2w2.services.AuthorsSevices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
  @Autowired
  private AuthorsSevices authorSevice;

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public Author saveAuthor(@RequestBody @Validated NewAuthorDTO body, BindingResult validation) {
    if (validation.hasErrors()) {
      throw new BadRequestException("Empty or not respected fields", validation.getAllErrors());
    } else {
      try {
        return authorSevice.save(body);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  @PostMapping("/{id}/uploadAvatarImg")
  public Author uploadAvatarImg(@PathVariable int id,
                                @RequestParam("avatar") MultipartFile body) throws IOException {
    return authorSevice.uploadImg(id, body);
  }

  @GetMapping("")
  public Page<Author> getAll(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "15") int size,
                             @RequestParam(defaultValue = "name") String orderBy) {
    return authorSevice.findAll(page, size, orderBy);
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
