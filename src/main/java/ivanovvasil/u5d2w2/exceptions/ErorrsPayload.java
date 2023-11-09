package ivanovvasil.u5d2w2.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
public class ErorrsPayload {
  private String message;
  private Date timestamp;
}
