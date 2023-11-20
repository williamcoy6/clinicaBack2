package co.edu.uniquindio.clinica.dto.PQRS;

import jakarta.validation.constraints.NotNull;

public record RegistroRespuestaDTO(
        @NotNull
        int codigo,
        @NotNull
        String mensaje
) {
}
