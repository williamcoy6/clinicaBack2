package co.edu.uniquindio.clinica.dto.admin;

import co.edu.uniquindio.clinica.modelo.Enum.Especializacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record ItemMedicoDTO(@NotBlank int codigo,
                            @NotBlank
                            @Length(max = 10, message = "Ingrese una cedula correcta")
                            String cedula,
                            @NotBlank String nombre,
                            String urlFoto,
                            @NotNull Especializacion especialidad) {
}
