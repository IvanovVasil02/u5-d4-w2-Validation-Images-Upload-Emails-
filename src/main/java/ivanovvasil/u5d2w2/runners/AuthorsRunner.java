package ivanovvasil.u5d2w2.runners;

import ivanovvasil.u5d2w2.entities.Author;
import ivanovvasil.u5d2w2.services.AuthorsSevices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class AuthorsRunner implements CommandLineRunner {
  @Autowired
  AuthorsSevices authorsSevices;

  @Override
  public void run(String... args) throws Exception {

    for (int i = 0; i < 10; i++) {
      Author newAuthor = Author.builder().build();
      authorsSevices.save(newAuthor);
    }

  }
}
