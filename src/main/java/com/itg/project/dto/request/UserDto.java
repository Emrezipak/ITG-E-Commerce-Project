package com.itg.project.dto.request;

import com.itg.project.entity.Role;
import com.itg.project.util.UniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class UserDto implements Serializable {

    @NotEmpty(message = "{user.name.validation.constraints.NotNull.message}")
    private String name;

    @NotEmpty(message = "{user.surname.validation.constraints.NotNull.message}")
    private String surname;

    @NotEmpty(message = "{user.email.validation.constraints.NotNull.message}")
    @Email(message = "{user.email.pattern.validation.constraints.NotNull.message}")
    @UniqueEmail
    private String email;

    @NotEmpty
    @Size(min = 8, message = "{user.password.pattern.validation.constraints.NotNull.message}")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).*$", message = "{user.password.pattern.validation.constraints.NotNull.message}")
    private String password;

    @NotEmpty
    private String phoneNumber;
}
