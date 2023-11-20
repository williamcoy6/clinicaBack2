package co.edu.uniquindio.clinica.controladores.controller;

import co.edu.uniquindio.clinica.dto.Clinica.ItemPqrsDTO;
import co.edu.uniquindio.clinica.dto.medico.ItemCitaDTO;
import co.edu.uniquindio.clinica.dto.paciente.*;
import co.edu.uniquindio.clinica.dto.Clinica.MensajeDTO;
import co.edu.uniquindio.clinica.modelo.Enum.Especializacion;
import co.edu.uniquindio.clinica.servicios.interfaces.PacienteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/paciente")
public class PacienteController {

    private final PacienteServicio pacienteServicio;

    @PostMapping("/registrarse")
    public ResponseEntity<MensajeDTO<String>> registrarse(@Valid @RequestBody RegistroPacienterDTO registroPacienteDTO) throws Exception {
        pacienteServicio.registrarse(registroPacienteDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Paciente resgistrado correctamente"));
    }

    @PutMapping("/editar-perfil/{codigo}")
    public ResponseEntity<MensajeDTO<String>> editarPerfil(@PathVariable int codigo, @Valid @RequestBody DetallePacienteDTO detallePacienteDTO) throws Exception {
        pacienteServicio.editarPerfil(codigo, detallePacienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Paciente actualizado exitosamente"));
    }

    @DeleteMapping("/eliminar/{codigoPaciente}")
    public ResponseEntity<MensajeDTO<String>> eliminarCuenta(@PathVariable int codigoPaciente) throws Exception {
        pacienteServicio.eliminarCuenta(codigoPaciente);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Paciente eliminado correctamente"));
    }

    @GetMapping("/detalle-paciente/{codigo}")
    public ResponseEntity<MensajeDTO<DetallePacienteDTO>> verDetallePaciente(@PathVariable int codigo) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, pacienteServicio.verDetallePaciente(codigo)));
    }

    @GetMapping("/listar-pqrs/{idPaciente}")
    public ResponseEntity<MensajeDTO<List<ItemPqrsDTO>>> listarPqrsPaciente(@PathVariable int idPaciente) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, pacienteServicio.listarPqrsPaciente(idPaciente)));
    }

    @GetMapping("/listar-citas-paciente/{codigoPaciente}")
    public ResponseEntity<MensajeDTO <List<ItemCitaPacienteDTO>>> listarCitasPaciente(@PathVariable int codigoPaciente) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, pacienteServicio.listarCitasPaciente(codigoPaciente)));
    }

    @GetMapping("/listar-citas-fecha")
    public ResponseEntity<MensajeDTO <List<ItemCitaDTO>>> filtrarCitasPorFecha(FiltrarSearchCitaDTO filtrarSearchCitaDTO) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, pacienteServicio.filtrarCitasPorFecha(filtrarSearchCitaDTO)));
    }

    @GetMapping("/listar-citas-medico")
    public ResponseEntity<MensajeDTO <List<ItemMedicoCitaDTO>>> filtrarMedicoCita(Especializacion especialidad, LocalDateTime fecha) throws Exception {
        return null;
    }

    @PostMapping("/agendar-cita")
    public ResponseEntity<MensajeDTO<String>> agendarCita(@Valid @RequestBody CitaPacienteDTO citaDTO) throws Exception {
        pacienteServicio.agendarCita(citaDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Cita agendada exitosamente"));
    }

    public int crearPQRS(PqrsPacienteDTO pqrsPacienteDTO) throws Exception {
        return 0;
    }

    public int responderPQRS(RespuestaPacientePqrsDTO respuestaPacientePqrsDTO) throws Exception {
        return 0;
    }

    // Metdoo sin uso
    public void enviarLinkRecuperacion() throws Exception {
    }


}
