package co.edu.uniquindio.clinica.dto.Cita;

import co.edu.uniquindio.clinica.modelo.Enum.Especializacion;
import co.edu.uniquindio.clinica.modelo.Enum.EstadoCita;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CitaDTOAdmin( int codigoCita,
                           @NotBlank String cedulaPaciente,
                           @NotBlank String nombrePaciente,
                           @NotBlank String nombreMedico,
                           @NotNull LocalDateTime fecha,
                           @NotNull Especializacion especializacion,
                           @NotNull EstadoCita estadoCita ) {

}
