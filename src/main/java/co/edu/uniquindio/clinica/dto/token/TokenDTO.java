package co.edu.uniquindio.clinica.dto.token;

import jakarta.validation.constraints.NotBlank;
public record TokenDTO (
        @NotBlank
        String token){
}