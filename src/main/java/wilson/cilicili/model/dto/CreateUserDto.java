package wilson.cilicili.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(
        @NotBlank String username,
        @NotBlank String password,
        @Email String email
) {
}
