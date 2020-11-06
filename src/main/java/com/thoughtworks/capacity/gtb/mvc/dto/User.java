package com.thoughtworks.capacity.gtb.mvc.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {


    int id;

    @NotBlank
    @Length(min = 3, max = 10)
    @Pattern(regexp = "^[a-zA-Z0-9_]+$")
    private String username;

    @NotBlank
    @Length(min = 5, max = 12)
    private String password;

    @Email
    private String email;

}
