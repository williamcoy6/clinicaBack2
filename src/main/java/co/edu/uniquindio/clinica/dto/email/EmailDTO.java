package co.edu.uniquindio.clinica.dto.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailDTO(
        @NotBlank
        @Email(message = "Ingrese un correo valido")
        String remitente,
        @NotBlank
        @Email(message = "Ingrese un correo valido")
        String destinatario,
        String asunto,
        String mensaje
) {
}