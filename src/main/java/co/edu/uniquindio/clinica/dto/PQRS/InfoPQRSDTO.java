package co.edu.uniquindio.clinica.dto.PQRS;

import co.edu.uniquindio.clinica.modelo.Enum.Especializacion;
import co.edu.uniquindio.clinica.modelo.Enum.EstadoCita;
import co.edu.uniquindio.clinica.modelo.Enum.EstadoPqrs;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record InfoPQRSDTO(int codigo,
                          @NotNull EstadoPqrs estado,
                          @NotBlank String motivo,
                          @NotBlank String nombrePaciente,
                          @NotBlank String nombreMedico,
                          @NotNull Especializacion especializacion,
                          @NotNull LocalDateTime fecha,
                          ArrayList<Object> objects)  {

}


