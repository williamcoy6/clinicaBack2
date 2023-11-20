package co.edu.uniquindio.clinica.dto.PQRS;

import co.edu.uniquindio.clinica.modelo.Enum.EstadoPqrs;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ItemPqrsAdminDTO(
        @NotBlank int codigo,
        @NotNull EstadoPqrs estadoPqrs,
        @NotNull LocalDateTime fecha,
        @NotBlank String nombrePaciente) {
}

