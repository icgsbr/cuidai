package br.com.example.cuidai.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormDTO {
    @NotBlank
    private String userEmail;

    @NotBlank
    private Double _achievement;

    @NotBlank
    private Double _supportingOthers;

    @NotBlank
    private Double _flow;

    @NotBlank
    private Double _coreCircle;

    @NotBlank
    private Double _placesVisited;

    @NotBlank
    private Double _todoCompleted;

    @NotBlank
    private Double _timeForPassion;

    @NotBlank
    private Double _fruitsAndVeggies;

    @NotBlank
    private Double _personalAwards;
}
