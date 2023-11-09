package ivanovvasil.u5d2w2.services;

import ivanovvasil.u5d2w2.entities.Author;
import ivanovvasil.u5d2w2.exceptions.BadRequestException;
import ivanovvasil.u5d2w2.exceptions.NotFoundException;
import ivanovvasil.u5d2w2.payloads.authors.NewAuthorDTO;
import ivanovvasil.u5d2w2.repositories.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AuthorsSevices {
  @Autowired
  private AuthorsRepository authorsRepository;

  public Author saveRunnerUser(Author author) {
    return authorsRepository.save(author);
  }

  public Author save(NewAuthorDTO body) throws IOException {
    authorsRepository.findByEmail(body.email()).ifPresent(author -> {
      throw new BadRequestException("L'email " + author.getEmail() + " è già utilizzata!");
    });

    Author newAuthor = new Author();
    newAuthor.setName(body.name());
    newAuthor.setSurname(body.surname());
    newAuthor.setEmail(body.email());
    newAuthor.setBirthDate(body.birthDate());
    newAuthor.setAvatar("http://ui-avatars.com/api/?name=" + newAuthor.getName() + "+" + newAuthor.getSurname());
    return authorsRepository.save(newAuthor);
  }

  public Page<Author> findAll(int page, int size, String orderBy) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
    return authorsRepository.findAll(pageable);
  }

  public List<Author> findAll() {
    return authorsRepository.findAll();
  }

  public Author findById(int id) throws NotFoundException {
    return authorsRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
  }

  public void findByIdAndDelete(int id) throws NotFoundException {
    authorsRepository.delete(this.findById(id));
  }

  public Author findByIdAndUpdate(int id, Author body) throws NotFoundException {
    Author found = this.findById(id);
    found.setName(body.getName());
    found.setSurname(body.getSurname());
    found.setEmail(body.getEmail());
    found.setBirthDate(body.getBirthDate());
    return authorsRepository.save(found);
  }
}
