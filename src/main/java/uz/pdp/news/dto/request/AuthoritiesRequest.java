package uz.pdp.news.dto.request;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import uz.pdp.news.enums.AuthorityType;
import uz.pdp.news.validator.IsValidEnum;

@Data
public class AuthoritiesRequest {
    @NotNull(message = "authorities must not be null")
    @NotEmpty(message = "authorities must not be empty")
    private Set<
        @NotNull(message = "authority must not be null")
        @NotBlank(message = "authority must not be empty")
        @IsValidEnum(enumClazz = AuthorityType.class) String> authorities;
}
