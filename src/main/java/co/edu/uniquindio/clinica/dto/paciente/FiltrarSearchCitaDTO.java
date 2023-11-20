package co.edu.uniquindio.clinica.dto.paciente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDateTime;

public record FiltrarSearchCitaDTO(int codigoPaciente,
                                   @NotBlank String nombreMedico,
                                   @NotNull LocalDateTime fechaCita) {
}
