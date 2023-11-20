package co.edu.uniquindio.clinica.dto.Clinica;

import co.edu.uniquindio.clinica.modelo.Enum.EstadoPqrs;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ItemPqrsDTO(int codigoRadicacion,
                          @NotBlank String detalle,
                          @NotNull LocalDateTime fecha,
                          @NotNull EstadoPqrs estadoPqrs) {
}
