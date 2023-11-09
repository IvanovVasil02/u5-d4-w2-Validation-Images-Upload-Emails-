package ivanovvasil.u5d2w2.services;

import ivanovvasil.u5d2w2.entities.Author;
import ivanovvasil.u5d2w2.entities.BlogPost;
import ivanovvasil.u5d2w2.entities.NewPostPayload;
import ivanovvasil.u5d2w2.exceptions.NotFoundException;
import ivanovvasil.u5d2w2.repositories.BlogPostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostsSevices {
  @Autowired
  private BlogPostsRepository blogPostsRepository;
  @Autowired
  private AuthorsSevices authorsSevices;

  public BlogPost save(BlogPost body) {
    return blogPostsRepository.save(body);
  }

  public BlogPost saveNewPost(NewPostPayload body) throws NotFoundException {
    Author authorFound = authorsSevices.findById(body.getAuthorId());
    BlogPost newBlogPost = BlogPost.builder()
            .categoria(body.getCategoria())
            .titolo(body.getTitolo())
            .postContent(body.getPostContent())
            .author(authorFound)
            .cover("https://picsum.photos/200/300")
            .build();
    return blogPostsRepository.save(newBlogPost);
  }

  public List<BlogPost> findAll() {
    return blogPostsRepository.findAll();
  }

  public Page<BlogPost> findAll(int page, int size, String orderBy) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

    return blogPostsRepository.findAll(pageable);
  }

  public BlogPost findById(int id) throws NotFoundException {
    return blogPostsRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
  }

  public void findByIdAndDelete(int id) throws NotFoundException {
    blogPostsRepository.delete(this.findById(id));
  }

  public BlogPost findByIdAndUpdate(int id, BlogPost body) throws NotFoundException {
    BlogPost found = this.findById(id);
    found.setCategoria(body.getCategoria());
    found.setTitolo(body.getTitolo());
    return blogPostsRepository.save(found);
  }
}
