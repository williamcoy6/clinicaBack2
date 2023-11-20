package co.edu.uniquindio.clinica.dto.medico;

import co.edu.uniquindio.clinica.dto.paciente.MedicamentoDTO;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

public record RegistroAtencionDTO(

        @NotBlank(message = "El campo es obligatorio, no debe estar vacio")
        int codigoCita,
        @NotBlank(message = "El campo es obligatorio")
        int codigoMedico,
        @Length(max = 400, message = "Los campos no pueden superar los 400 caracteres")
        String notasMedicas,
        @Length(max = 400, message = "Los campos no pueden superar los 400 caracteres")
        String tratamiento,
        @NotBlank
        @Length(max = 400, message = "Los campos no pueden superar los 400 caracteres")
        String sintomas,
        @NotEmpty(message = "El campo es obligatorio, no debe estar vacio")
        List<MedicamentoDTO>  medicamentos,
        @Length(max = 400, message = "La descripción  no puede superar los 400 caracteres")
        String descripcionReceta,
        @Length(max = 400, message = "Los campos no pueden superar los 400 caracteres")
        String diagnostico




) {
}
