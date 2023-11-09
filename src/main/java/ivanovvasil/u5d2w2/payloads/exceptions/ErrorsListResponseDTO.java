package ivanovvasil.u5d2w2.payloads.exceptions;

import java.util.Date;
import java.util.List;

public record ErrorsListResponseDTO(String message,
                                    Date timeStamp,
                                    List<String> errorsList) {
}
