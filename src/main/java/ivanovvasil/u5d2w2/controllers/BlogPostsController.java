package ivanovvasil.u5d2w2.controllers;

import ivanovvasil.u5d2w2.entities.BlogPost;
import ivanovvasil.u5d2w2.entities.NewPostPayload;
import ivanovvasil.u5d2w2.services.BlogPostsSevices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostsController {
  @Autowired
  private BlogPostsSevices blogPostsSevices;

  @GetMapping("")
  public Page<BlogPost> getAll(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "15") int size,
                               @RequestParam(defaultValue = "readingTime") String orderBy) {
    return blogPostsSevices.findAll(page, size, orderBy);
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public BlogPost saveBlogPost(@RequestBody NewPostPayload body) {

    return blogPostsSevices.saveNewPost(body);
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
