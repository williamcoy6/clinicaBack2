package co.edu.uniquindio.clinica.dto.paciente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CitaPacienteDTO(@NotBlank String motivo,
                              @NotNull LocalDateTime fecha,
                              @NotBlank int codigoMedico,
                              @NotBlank int codigoPaciente) {
}
