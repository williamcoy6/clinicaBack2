package co.edu.uniquindio.clinica.dto.Cita;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailDTO(@NotBlank String asunto,
                       @NotBlank
                       @Email String destinatario,
                       @NotBlank String mensaje
) {
}
