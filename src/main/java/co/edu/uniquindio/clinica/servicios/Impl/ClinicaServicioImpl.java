
package co.edu.uniquindio.clinica.servicios.Impl;

import co.edu.uniquindio.clinica.Repositorios.*;
import co.edu.uniquindio.clinica.dto.Clinica.MensajeDTO2;
import co.edu.uniquindio.clinica.modelo.Entidades.*;
import co.edu.uniquindio.clinica.modelo.Enum.Ciudad;
import co.edu.uniquindio.clinica.modelo.Enum.Eps;
import co.edu.uniquindio.clinica.modelo.Enum.Especializacion;
import co.edu.uniquindio.clinica.modelo.Enum.TipoSangre;
import co.edu.uniquindio.clinica.servicios.interfaces.ClinicaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClinicaServicioImpl implements ClinicaServicio {

    private final RespuestaAdminRepo respuestaAdminRepo;
    private final RespuestaPacienteRepo respuestaPacienteRepo;
    private final MedicoRepo medicoRepo;
    private final PacienteRepo pacienteRepo;

    @Override
    public List<Ciudad> listarCiudades(){
        return List.of( Ciudad.values() );
    }

    @Override
    public List<Eps> listarEps() {return List.of(Eps.values());}


    @Override
    public List<Especializacion> listarEspecializacion() {return List.of(Especializacion.values());}

    @Override
    public List<TipoSangre> listarTipoDeSangre() {return List.of(TipoSangre.values());}

    @Override
    public void cambiarPassword(int codigoUsuario, String nuevaPassword) throws Exception {
        // Intentar encontrar un médico con el código de usuario proporcionado
        Optional<Medico> medico = medicoRepo.findById(codigoUsuario);

        // Verificar si se encontró un médico y actualizar la contraseña si es así
        if (medico.isPresent()) {
            actualizarContrasenia(medico.get(), nuevaPassword, medicoRepo);
            return;
        }

        // Intentar encontrar un paciente con el código de usuario proporcionado
        Optional<Paciente> paciente = pacienteRepo.findById(codigoUsuario);

        // Verificar si se encontró un paciente y actualizar la contraseña si es así
        if (paciente.isPresent()) {
            actualizarContrasenia(paciente.get(), nuevaPassword, pacienteRepo);
            return;
        }

        // Lanzar una excepción si no se encontró ningún usuario con el código proporcionado
        throw new Exception("El usuario con el código " + codigoUsuario + " no existe");
    }
    //  Método para listar los mensajes asociados a una PQRS .
    @Override
    public List<MensajeDTO2> listarPQRS(int codigoPqrs) throws Exception {
        // Inicialización de la lista de mensajes
        List<MensajeDTO2> mensajeDTO2s = new ArrayList<>();

        // Obtener respuestas de administradores asociadas a la PQRS
        List<RespuestaAdmin> respuestaAdmins = respuestaAdminRepo.findAllByPqrs_Codigo(codigoPqrs);

        // Verificar si hay respuestas de administradores y agregarlas a la lista de mensajes
        if (!respuestaAdmins.isEmpty()) {
            mensajeDTO2s.addAll(mapearRespuestasAdmin(respuestaAdmins));
        }

        // Obtener respuestas de pacientes asociadas a la PQRS
        List<RespuestaPaciente> respuestaPacientes = respuestaPacienteRepo.findByPqrs_Codigo(codigoPqrs);

        // Verificar si hay respuestas de pacientes y agregarlas a la lista de mensajes
        if (!respuestaPacientes.isEmpty()) {
            mensajeDTO2s.addAll(mapearRespuestasPacientes(respuestaPacientes));
        }

        // Ordenar la lista de mensajes por el código de la PQRS
        mensajeDTO2s.sort(Comparator.comparing(MensajeDTO2::codigoPqrs));

        // Verificar si se encontraron respuestas, si no, lanzar una excepción
        if (mensajeDTO2s.isEmpty()) {
            throw new Exception("No se encontraron respuestas para la PQRS con el código: " + codigoPqrs);
        }

        // Paso 8: Devolver la lista de mensajes
        return mensajeDTO2s;
    }

    private List<MensajeDTO2> mapearRespuestasAdmin(List<RespuestaAdmin> respuestaAdmins) {
        return respuestaAdmins.stream()
                .map(respuestaAdmin -> new MensajeDTO2(
                        respuestaAdmin.getId(),
                        respuestaAdmin.getAdministrador().getCodigo(),
                        respuestaAdmin.getAdministrador().getCorreo(),
                        respuestaAdmin.getMensaje(),
                        respuestaAdmin.getFecha(),
                        respuestaAdmin.getPqrs().getCodigo()))
                .collect(Collectors.toList());
    }

    private List<MensajeDTO2> mapearRespuestasPacientes(List<RespuestaPaciente> respuestaPacientes) {
        return respuestaPacientes.stream()
                .map(respuestaPaciente -> new MensajeDTO2(
                        respuestaPaciente.getId(),
                        respuestaPaciente.getPaciente().getCodigo(),
                        respuestaPaciente.getPaciente().getCorreo(),
                        respuestaPaciente.getMensaje(),
                        respuestaPaciente.getFecha(),
                        respuestaPaciente.getPqrs().getCodigo()))
                .collect(Collectors.toList());
    }

    // Método privado para actualizar la contraseña de un usuario genérico.
    private <T extends Usuario> void actualizarContrasenia(T usuario, String nuevaPassword, JpaRepository<T, Integer> repository) {

        // Codificar la nueva contraseña utilizando BCrypt
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode(nuevaPassword);

        // Actualizar la contraseña del usuario y guardar los cambios en el repositorio
        usuario.setContrasena(passwordEncriptada);
        repository.save(usuario);
    }

}

