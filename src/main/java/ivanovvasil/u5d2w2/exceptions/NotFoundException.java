package ivanovvasil.u5d2w2.exceptions;

public class NotFoundException extends RuntimeException {
  public NotFoundException(int id) {
    super("There is no user with this id");
  }
}
