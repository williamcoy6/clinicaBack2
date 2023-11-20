package co.edu.uniquindio.clinica.dto.Clinica;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record MensajeDTO2(int codigo,
                          int codigoCuenta,
                          @NotBlank String nombreUsuario,
                          @NotBlank
                          @Length(max = 400, message = "no puede pasarse de 400 caracteres")
                          String mensaje,
                          @NotNull LocalDateTime fecha,
                          int codigoPqrs) {
}
