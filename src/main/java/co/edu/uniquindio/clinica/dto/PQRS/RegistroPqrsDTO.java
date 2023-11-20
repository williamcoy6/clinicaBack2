package co.edu.uniquindio.clinica.dto.PQRS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record RegistroPqrsDTO(
        @NotNull
        @Positive
        int codigoCita,
        @NotBlank(message = "Debe ingresar el mensaje ")
        @Length(max = 300, message = "El mensaje tiene 300 caracteres como maximo, no puede superar el limite")
        String mensaje
) {
}