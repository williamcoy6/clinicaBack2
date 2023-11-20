package co.edu.uniquindio.clinica.dto.admin;


import co.edu.uniquindio.clinica.modelo.Enum.Dia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record HorarioDTO (@NotBlank Dia dia,
                          @NotBlank LocalTime horaInicio,
                          @NotBlank LocalTime horaSalida) {
}