package ivanovvasil.u5d2w2.repositories;


import ivanovvasil.u5d2w2.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Integer> {
  Optional<Author> findByEmail(String email);
}
