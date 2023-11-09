package ivanovvasil.u5d2w2.controllers;

import ivanovvasil.u5d2w2.entities.BlogPost;
import ivanovvasil.u5d2w2.exceptions.BadRequestException;
import ivanovvasil.u5d2w2.payloads.blogPosts.NewBlogPostDTO;
import ivanovvasil.u5d2w2.services.BlogPostsSevices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostsController {
  @Autowired
  private BlogPostsSevices blogPostsSevices;

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public BlogPost saveBlogPost(@RequestBody @Validated NewBlogPostDTO body, BindingResult validation) {
    if (validation.hasErrors()) {
      throw new BadRequestException(validation.getAllErrors());
    } else {
      try {
        return blogPostsSevices.saveNewPost(body);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }


  }

  @GetMapping("")
  public Page<BlogPost> getAll(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "15") int size,
                               @RequestParam(defaultValue = "readingTime") String orderBy) {
    return blogPostsSevices.findAll(page, size, orderBy);
  }

  @GetMapping("/{id}")
  public BlogPost findById(@PathVariable int id) {
    return blogPostsSevices.findById(id);
  }

  @PutMapping("/{id}")
  public BlogPost findByIdAndUpdate(@PathVariable int id, @RequestBody BlogPost body) {
    return blogPostsSevices.findByIdAndUpdate(id, body);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void findByIdAndDelete(@PathVariable int id) {
    blogPostsSevices.findByIdAndDelete(id);
  }
}
