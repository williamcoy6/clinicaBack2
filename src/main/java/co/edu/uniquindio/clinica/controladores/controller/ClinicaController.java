package co.edu.uniquindio.clinica.controladores.controller;

import co.edu.uniquindio.clinica.dto.Clinica.MensajeDTO;
import co.edu.uniquindio.clinica.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.clinica.dto.paciente.RegistroPacienterDTO;
import co.edu.uniquindio.clinica.modelo.Enum.Ciudad;
import co.edu.uniquindio.clinica.modelo.Enum.Eps;
import co.edu.uniquindio.clinica.modelo.Enum.Especializacion;
import co.edu.uniquindio.clinica.modelo.Enum.TipoSangre;
import co.edu.uniquindio.clinica.servicios.interfaces.ClinicaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/clinica")
@RequiredArgsConstructor
public class ClinicaController {

    private final ClinicaServicio clinicaServicio;

    @GetMapping("/lista-ciudades")
    public ResponseEntity<MensajeDTO<List<Ciudad>>> listarCiudades()  {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clinicaServicio.listarCiudades()));
    }

    @GetMapping("/lista-eps")
    public ResponseEntity<MensajeDTO<List<Eps>>>listarEps()  {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clinicaServicio.listarEps()));
    }

    @GetMapping("/lista-especializacion")
    public ResponseEntity<MensajeDTO<List<Especializacion>>> listarEspecializacion()  {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clinicaServicio.listarEspecializacion()));
    }
    @GetMapping("/lista-tipo-sangre")
    public ResponseEntity<MensajeDTO<List<TipoSangre>>> listarTipoDeSangre() {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clinicaServicio.listarTipoDeSangre()));
    }

    }
