package co.edu.uniquindio.clinica.dto.paciente;

import co.edu.uniquindio.clinica.modelo.Enum.Ciudad;
import co.edu.uniquindio.clinica.modelo.Enum.Eps;
import co.edu.uniquindio.clinica.modelo.Enum.TipoSangre;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record DetallePacienteDTO(int codigo,
                                 @NotBlank
                                 @Length(max = 10, message="La cedula debe tener máximo 10 caracteres")
                                 String cedula,
                                 @NotBlank
                                 String nombre,
                                 @NotBlank
                                 @Length(max = 10, message="El telefono debe tener máximo 10 caracteres")
                                 String telefono,
                                 @NotBlank
                                 String urlFoto,
                                 @NotBlank
                                 Ciudad ciudad,
                                 @NotNull
                                 @Past(message = "Seleccione una fecha de nacimiento correcta")
                                 LocalDate fechaNacimiento,
                                 @NotBlank
                                 String alergias,
                                 @NotBlank
                                 Eps eps,
                                 @NotBlank
                                 TipoSangre tipoSangre,

                                 @Email(message = "Ingrese una dirección de correo electrónico válida")
                                 @Length(max = 50, message = "El correo debe tener máximo 50 caracteres")
                                 String correo) {

}

