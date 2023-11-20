package co.edu.uniquindio.clinica.dto.paciente;

import co.edu.uniquindio.clinica.modelo.Enum.EstadoCita;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ItemCitaPacienteDTO(@NotBlank String motivo,

                                  @NotNull LocalDateTime fechaCreacion,
                                  @NotNull LocalDateTime fechaCita,
                                  @NotNull EstadoCita estadoCita,
                                  @NotBlank String nombreMedico) {
}
