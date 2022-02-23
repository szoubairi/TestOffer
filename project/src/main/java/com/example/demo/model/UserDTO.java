package com.example.demo.model;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.example.demo.validation.BirthDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Schema(description = "User name", example = "Saad")
    @NotBlank(message = "Name may not be empty")
    private String name;

    @Schema(description = "Date of birth, yyyy-mm-dd", example = "2000-04-05")
    @NotNull
    @BirthDate
    private LocalDate birth;

    @Schema(description = "Country, Only France is accepted", example = "France")
    @NotNull
    @Pattern(regexp = "France", message = "Only French people are accepted")
    private String country;

    @Schema(description = "French phone number. Optional", nullable = true, example = "0655665566")
    private String phone;

    @Schema(description = "Gender, f for female, m for male. Optional", nullable = true, example = "f")
    private String gender;
}
