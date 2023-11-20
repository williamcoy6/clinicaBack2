package co.edu.uniquindio.clinica.dto.paciente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record ItemMedicoCitaDTO(@NotBlank int codigoMedico,
                                @NotBlank String nombreMedico,
                                @NotNull LocalDateTime fecha) {
}
