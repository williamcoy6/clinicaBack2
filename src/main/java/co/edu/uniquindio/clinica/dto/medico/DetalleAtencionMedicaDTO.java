package co.edu.uniquindio.clinica.dto.medico;


import co.edu.uniquindio.clinica.modelo.Enum.Especializacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record DetalleAtencionMedicaDTO(
        @NotBlank int codigoCita,
        @NotBlank String nombrePaciente,
        @NotBlank String nombreMedico,
        @NotNull Especializacion especialidad,
        @NotNull LocalDateTime fechaAtencion,
        @NotBlank String notaMedica,
        @NotBlank String diagnostico
) {
}
