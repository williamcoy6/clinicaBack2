package co.edu.uniquindio.clinica.dto.admin;

import jakarta.validation.constraints.NotBlank;

public record RespuestaAdminDTO(int codigoPqrs,
                                int codigoAdmin,
                                @NotBlank String mensaje) {
}
