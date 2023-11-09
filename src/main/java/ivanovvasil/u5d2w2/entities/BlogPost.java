package ivanovvasil.u5d2w2.entities;

import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Locale;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder(builderClassName = "BlogPostBuilder")
public class BlogPost {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String categoria;
  private String titolo;
  private String cover;
  private String postContent;
  private LocalTime readingTime;
  @ManyToOne
  @JoinColumn(name = "author_id")
  private Author author;

  public static class BlogPostBuilder {
    Faker f = new Faker(Locale.ITALY);
    private String categoria = f.book().genre();
    private String titolo = f.book().title();
    private String cover = "https://picsum.photos/200/300";
    private String postContent = f.lorem().paragraph();
    private LocalTime readingTime = LocalTime.now();
  }
}
