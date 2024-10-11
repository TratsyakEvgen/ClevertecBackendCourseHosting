package ru.clevertec.hosting.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NotNull(message = "UserDTO must not be null")
public class UpdateUserDTO {
    @NotBlank(message = "Name mustn't be empty")
    private String name;
    @NotBlank(message = "Name mustn't be empty")
    private String nickname;
    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Incorrect format email")
    private String email;
}
