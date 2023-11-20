package co.edu.uniquindio.clinica.dto.paciente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record MedicamentoDTO(
        @NotBlank(message = "El campo es obligatorio")
        @Length(max = 100, message = "El nombre no puede superar los 100 caracteres")
        String nombre,
        @NotBlank(message = "El campo es obligatorio")
        @Positive(message = "Ingrese una cantidad > a 0 cero")
        int cantidad,
        @NotBlank(message = "El campo es obligatorio ")
        @Length(max = 50, message = "el tipo de uso no puede superar los 50 caracteres")
        String uso,
        @NotBlank(message = "El campo es obligatorio")
        @Length(max = 100, message = "La dosis no puede superar los 100 caracteres")
        String dosis
) {
}

