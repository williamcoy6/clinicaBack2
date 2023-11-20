package co.edu.uniquindio.clinica.servicios.Impl;

import co.edu.uniquindio.clinica.Repositorios.AdministradorRepo;
import co.edu.uniquindio.clinica.Repositorios.MedicoRepo;
import co.edu.uniquindio.clinica.Repositorios.PacienteRepo;
import co.edu.uniquindio.clinica.dto.admin.LoginDTO;
import co.edu.uniquindio.clinica.dto.token.TokenDTO;
import co.edu.uniquindio.clinica.modelo.Entidades.Cuenta;
import co.edu.uniquindio.clinica.modelo.Entidades.Medico;
import co.edu.uniquindio.clinica.modelo.Entidades.Paciente;
import co.edu.uniquindio.clinica.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.clinica.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AutenticacionServicioImpl implements AutenticacionServicio {

    private final PacienteRepo pacienteRepo;
    private final MedicoRepo medicoRepo;
    private final AdministradorRepo administradorRepo;
    private final JWTUtils jwtUtils;


    @Override
    public TokenDTO login(LoginDTO loginDTO) throws Exception {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //se busca en paciente

        Optional<Cuenta> cuentaOptional = Optional.ofNullable(pacienteRepo.findByCorreo(loginDTO.correo()));

        if (cuentaOptional.isEmpty()) {
            throw new Exception("No existe el correo ingresado");
        }

        //Buscarlo en medico

        Optional<Cuenta> cuentaOptional1 = Optional.ofNullable(medicoRepo.findByCorreo(loginDTO.correo()));

        if (cuentaOptional1.isEmpty()) {
            throw new Exception("No existe el correo ingresado");
        }

        //Buscarlo en Amdin

        Optional<Cuenta> cuentaOptional2 = Optional.ofNullable(administradorRepo.findByCorreo(loginDTO.correo()));

        if (cuentaOptional2.isEmpty()) {
            throw new Exception("No existe el correo ingresado");
        }

        Cuenta cuenta = cuentaOptional.get();

        if (!passwordEncoder.matches(loginDTO.contrasenia(), cuenta.getContrasena())) {
            throw new Exception("La contraseña ingresada es incorrecta");
        }

        return new TokenDTO(crearToken(cuenta));
    }

    private String crearToken(Cuenta cuenta) {
        String rol;
        String nombre;
        if (cuenta instanceof Paciente) {
            rol = "paciente";
            nombre = ((Paciente) cuenta).getNombre();
        } else if (cuenta instanceof Medico) {
            rol = "medico";
            nombre = ((Medico) cuenta).getNombre();
        } else {
            rol = "admin";
            nombre = "Administrador";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", rol);
        map.put("nombre", nombre);
        map.put("id", cuenta.getCodigo());

        return jwtUtils.generarToken(cuenta.getCorreo(), map);
    }


}