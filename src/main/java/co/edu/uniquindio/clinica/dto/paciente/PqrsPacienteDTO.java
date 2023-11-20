package co.edu.uniquindio.clinica.dto.paciente;

import jakarta.validation.constraints.NotBlank;

public record PqrsPacienteDTO(int codigoCita,
                              @NotBlank
                              String motivo) {
}
