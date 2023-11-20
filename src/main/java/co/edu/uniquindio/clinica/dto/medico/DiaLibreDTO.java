package co.edu.uniquindio.clinica.dto.medico;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DiaLibreDTO(int codigoMedico, @NotNull LocalDateTime fecha) {
}
