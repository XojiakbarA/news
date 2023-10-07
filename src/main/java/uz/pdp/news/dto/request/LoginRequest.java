package uz.pdp.news.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotNull(message = "username must not be null")
    @NotBlank(message = "username must not be empty")
    private String username;

    @NotNull(message = "password must not be null")
    @NotBlank(message = "password must not be empty")
    private String password;
}
