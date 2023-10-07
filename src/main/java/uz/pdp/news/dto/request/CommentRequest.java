package uz.pdp.news.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import uz.pdp.news.marker.OnCreate;

@Data
public class CommentRequest {
    @NotNull(message = "content must not be null", groups = OnCreate.class)
    @NotBlank(message = "content must not be empty", groups = OnCreate.class)
    private String content;

    @NotNull(message = "postId must not be null", groups = OnCreate.class)
    @Positive(message = "postId must be positive")
    private Long postId;
}
