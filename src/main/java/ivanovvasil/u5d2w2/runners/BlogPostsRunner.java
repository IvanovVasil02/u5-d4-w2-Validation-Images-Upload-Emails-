package ivanovvasil.u5d2w2.runners;

import ivanovvasil.u5d2w2.entities.Author;
import ivanovvasil.u5d2w2.entities.BlogPost;
import ivanovvasil.u5d2w2.services.AuthorsSevices;
import ivanovvasil.u5d2w2.services.BlogPostsSevices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@Order(2)
public class BlogPostsRunner implements CommandLineRunner {
  @Autowired
  private BlogPostsSevices blogPostsSevices;
  @Autowired
  private AuthorsSevices authorsSevices;

  @Override
  public void run(String... args) throws Exception {
    List<Author> authors = authorsSevices.findAll();

    for (int i = 0; i < 10; i++) {
      Author randomAuthor = authors.get(new Random().nextInt(0, authors.size() - 1));
      BlogPost newBlogPost = BlogPost.builder().author(randomAuthor).build();
      blogPostsSevices.save(newBlogPost);
    }

  }
}
