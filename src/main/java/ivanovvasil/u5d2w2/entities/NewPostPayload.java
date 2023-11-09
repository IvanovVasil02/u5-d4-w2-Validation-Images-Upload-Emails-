package ivanovvasil.u5d2w2.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NewPostPayload {
  private String categoria;
  private String titolo;
  private String postContent;
  private int AuthorId;
}
