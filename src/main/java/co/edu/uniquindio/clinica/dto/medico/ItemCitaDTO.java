package co.edu.uniquindio.clinica.dto.medico;

import co.edu.uniquindio.clinica.modelo.Enum.EstadoCita;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ItemCitaDTO(@NotBlank int codigoCita,
                          @NotBlank String cedulaPaciente,
                          @NotBlank String nombrePaciente,
                          @NotBlank String nombreMedico,
                          @NotNull EstadoCita estadoCita,
                          @NotNull LocalDateTime fecha,
                          @NotBlank String motivo) {
}
