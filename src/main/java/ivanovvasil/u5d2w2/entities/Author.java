package ivanovvasil.u5d2w2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Locale;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder(builderClassName = "AuthorsBuilder")
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private String surname;
  private String email;
  private String birthDate;
  private String avatar;
  @JsonIgnore
  @OneToMany(mappedBy = "author")
  private List<BlogPost> blogPosts;

  public static class AuthorsBuilder {
    Faker f = new Faker(Locale.ITALY);
    private String name = f.name().firstName();
    private String surname = f.name().firstName();
    private String email = f.internet().emailAddress();
    private String birthDate = String.valueOf(f.date().birthday());
  }
}
