package co.edu.uniquindio.clinica.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record ConsultaDTO(
        @NotNull int codigo,
        @NotBlank
        @Length(max = 10, message="La cedula debe tener máximo 10 caracteres")
        String cedulaPaciente,
        @NotBlank String nombrePaciente,
        @NotNull
        @Past(message = "Seleccione una fecha correcta")
        LocalDateTime fechaConsulta,
        @NotBlank String motivo
) {
}
