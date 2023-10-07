package uz.pdp.news.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import uz.pdp.news.marker.OnCreate;

@Data
public class UserRequest {
    @NotNull(message = "firstName must not be null", groups = OnCreate.class)
    @NotBlank(message = "firstName must not be empty", groups = OnCreate.class)
    private String firstName;

    @NotNull(message = "lastName must not be null", groups = OnCreate.class)
    @NotBlank(message = "lastName must not be empty", groups = OnCreate.class)
    private String lastName;

    @NotNull(message = "username must not be null", groups = OnCreate.class)
    @NotBlank(message = "username must not be empty", groups = OnCreate.class)
    private String username;

    @NotNull(message = "password must not be null", groups = OnCreate.class)
    @NotBlank(message = "password must not be empty", groups = OnCreate.class)
    private String password;

    @NotNull(message = "roleId must not be null", groups = OnCreate.class)
    @Positive(message = "roleId must be positive")
    private Long roleId;
}
