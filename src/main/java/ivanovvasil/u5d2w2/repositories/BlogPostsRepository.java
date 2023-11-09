package ivanovvasil.u5d2w2.repositories;


import ivanovvasil.u5d2w2.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostsRepository extends JpaRepository<BlogPost, Integer> {

}
