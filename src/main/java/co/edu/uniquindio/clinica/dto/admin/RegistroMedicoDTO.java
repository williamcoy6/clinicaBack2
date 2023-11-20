package co.edu.uniquindio.clinica.dto.admin;

import co.edu.uniquindio.clinica.modelo.Enum.Ciudad;
import co.edu.uniquindio.clinica.modelo.Enum.Especializacion;
import co.edu.uniquindio.clinica.modelo.Enum.EstadoMedico;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record RegistroMedicoDTO(@NotNull @Length(max = 200) String nombre,
                                @NotNull @Length(max = 80) String correo,
                                @NotNull @Length(max = 10) String cedula,
                                @NotNull @Length(max = 20) String  celular,
                                String direccion,
                                @NotNull String password,
                                @NotNull Especializacion especializacion,
                                @NotNull String urlFoto,
                                @NotNull Ciudad cuidad,
                                List<HorarioDTO> horarios,
                                @NotNull EstadoMedico estadoMedico) {
}
