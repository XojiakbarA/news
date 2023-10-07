package uz.pdp.news.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import uz.pdp.news.marker.OnCreate;

@Data
public class PostRequest {
    @NotNull(message = "title must not be null", groups = OnCreate.class)
    @NotBlank(message = "title must not be empty", groups = OnCreate.class)
    private String title;

    @NotNull(message = "content must not be null", groups = OnCreate.class)
    @NotBlank(message = "content must not be empty", groups = OnCreate.class)
    private String content;
}
