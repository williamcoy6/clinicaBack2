package co.edu.uniquindio.clinica.dto.admin;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank String correo,
        @NotBlank String contrasenia) {
        }
