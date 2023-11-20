package co.edu.uniquindio.clinica.dto.admin;

import co.edu.uniquindio.clinica.modelo.Enum.Ciudad;
import co.edu.uniquindio.clinica.modelo.Enum.Especializacion;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalTime;
import java.util.List;

public record DetalleMedicoDTO(@NotNull int codigo,
                               @NotBlank String nombre,
                               @NotBlank
                               @Email(message = "Ingrese una dirección de correo electrónico válida")
                               String correo,
                               @NotBlank
                               @Length(max = 10, message="La cedula debe tener máximo 10 caracteres")
                               String cedula,
                               @NotBlank String celular,
                               @NotNull Especializacion especializacion,
                               @NotNull LocalTime horaInicio,
                               @NotNull LocalTime horaFin,
                               String urlFoto,
                               @NotNull Ciudad cuidad,
                               List<HorarioDTO>horarios
) {

}
