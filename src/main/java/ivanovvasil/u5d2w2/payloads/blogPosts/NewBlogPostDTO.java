package ivanovvasil.u5d2w2.payloads.blogPosts;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NewBlogPostDTO(
        @NotEmpty(message = "The category is a required field")
        @Size(min = 4, max = 30, message = "The name must be between 3 and 30 characters")
        String categoria,
        @NotEmpty(message = "The title is a required field")
        @Size(min = 4, max = 30, message = "The surname must be between 3 and 30 characters")
        String titolo,
        @NotEmpty(message = "The post content is a required field")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "The email entered is invalid")
        String postContent,
        @NotEmpty(message = "The id is a required field")
        int AuthorID
) {
}
