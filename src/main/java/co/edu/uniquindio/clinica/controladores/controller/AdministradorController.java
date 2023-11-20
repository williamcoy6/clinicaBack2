package co.edu.uniquindio.clinica.controladores.controller;

import co.edu.uniquindio.clinica.dto.Cita.CitaDTOAdmin;
import co.edu.uniquindio.clinica.dto.PQRS.InfoPQRSDTO;
import co.edu.uniquindio.clinica.dto.PQRS.ItemPqrsAdminDTO;
import co.edu.uniquindio.clinica.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.clinica.dto.admin.ItemMedicoDTO;
import co.edu.uniquindio.clinica.dto.admin.RegistroMedicoDTO;
import co.edu.uniquindio.clinica.dto.admin.RespuestaAdminDTO;
import co.edu.uniquindio.clinica.dto.Clinica.MensajeDTO;
import co.edu.uniquindio.clinica.modelo.Enum.EstadoPqrs;
import co.edu.uniquindio.clinica.servicios.interfaces.AdministradorServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/administrador")
public class AdministradorController {

    private final AdministradorServicio administradorServicio;

    @PostMapping("/crear-medico")
    public ResponseEntity<MensajeDTO<String>> crearMedico(@Valid @RequestBody RegistroMedicoDTO medico) throws Exception{
        administradorServicio.crearMedico(medico);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Medico creado exitosamente"));
    }

    @PutMapping("/actualizar-medico")
    public ResponseEntity<MensajeDTO<String>> actualizarMedico(@Valid @RequestBody DetalleMedicoDTO medicoDTO) throws Exception{
        administradorServicio.actualizarMedico(medicoDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Actualizado exitosamente"));
    }

    @DeleteMapping("/eliminar-medico/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarMedico(@PathVariable int codigo) throws Exception{
        administradorServicio.eliminarMedico(codigo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Eliminado exitosamente"));
    }

    @GetMapping("/listar-medicos")
    public ResponseEntity<MensajeDTO <List<ItemMedicoDTO>>> listarMedicos() throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, administradorServicio.listarMedicos())) ;
    }

    @GetMapping("/detalle-medico/{codigo}")
    public ResponseEntity<MensajeDTO <DetalleMedicoDTO>> obtenerMedico(@PathVariable int codigo) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, administradorServicio.obtenerMedico(codigo))) ;
    }

    @GetMapping("/listar-pqrs")
    public ResponseEntity<MensajeDTO<List<ItemPqrsAdminDTO>>> listarPQRS() throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, administradorServicio.listarPQRS()));
    }

    @PostMapping("/responder-pqrs")
    public ResponseEntity<MensajeDTO <String>> responderPQRS(@Valid @RequestBody RespuestaAdminDTO respuestaAdminDTO) throws Exception{
        administradorServicio.responderPQRS(respuestaAdminDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Respuesta generada"));
    }

    @GetMapping("/ver-detalle-pqrs/{codigo}")
    public ResponseEntity<MensajeDTO<InfoPQRSDTO>> verDetallePQRS(@PathVariable int codigo) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, administradorServicio.verDetallePQRS(codigo)));
    }

    @GetMapping("/listar-citas")
    public ResponseEntity<MensajeDTO<List<CitaDTOAdmin>>> listarCitas() throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, administradorServicio.listarCitas())) ;
    }

    public void cambiarEstadoPQRS(int codigoPQRS, EstadoPqrs estadoPQRS)throws Exception{

    }

}
