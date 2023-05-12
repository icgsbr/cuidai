package br.com.example.cuidai.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank
    private String _name;

    private String _lastName;

    @NotBlank
    private String _email;

    @NotBlank
    private String _password;

    @NotBlank
    private String _ageRange;

    @NotBlank
    private String _gender;

    @NotBlank
    private Double _height;

    @NotBlank
    private Double _weight;
}
