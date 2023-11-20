package co.edu.uniquindio.clinica.controladores.controller;

import co.edu.uniquindio.clinica.dto.admin.LoginDTO;
import co.edu.uniquindio.clinica.dto.Clinica.MensajeDTO;
import co.edu.uniquindio.clinica.dto.paciente.RegistroPacienterDTO;
import co.edu.uniquindio.clinica.dto.token.TokenDTO;
import co.edu.uniquindio.clinica.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.clinica.servicios.interfaces.PacienteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionController {

    private final AutenticacionServicio autenticacionServicio;
    private final PacienteServicio pacienteServicio;

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO<TokenDTO>> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
        TokenDTO tokenDTO = autenticacionServicio.login(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));
    }
    @PostMapping("/registrarse")
    public ResponseEntity<MensajeDTO<TokenDTO>> registrarse(@Valid @RequestBody RegistroPacienterDTO registroPacienterDTO) throws Exception {
       pacienteServicio.registrarse(registroPacienterDTO);
       return  ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(
               false,"Usuario creado correctamente"));
    }

}