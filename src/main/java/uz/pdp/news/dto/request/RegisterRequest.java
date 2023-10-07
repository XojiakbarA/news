package uz.pdp.news.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotNull(message = "firstName must not be null")
    @NotBlank(message = "firstName must not be empty")
    private String firstName;

    @NotNull(message = "lastName must not be null")
    @NotBlank(message = "lastName must not be empty")
    private String lastName;

    @NotNull(message = "username must not be null")
    @NotBlank(message = "username must not be empty")
    private String username;

    @NotNull(message = "password must not be null")
    @NotBlank(message = "password must not be empty")
    private String password;
}
