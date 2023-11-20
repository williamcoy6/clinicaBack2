package co.edu.uniquindio.clinica.servicios.Impl;

import co.edu.uniquindio.clinica.Repositorios.*;
import co.edu.uniquindio.clinica.dto.Cita.EmailDTO;
import co.edu.uniquindio.clinica.dto.Clinica.ItemPqrsDTO;
import co.edu.uniquindio.clinica.dto.medico.ItemCitaDTO;
import co.edu.uniquindio.clinica.dto.paciente.*;
import co.edu.uniquindio.clinica.modelo.Entidades.*;
import co.edu.uniquindio.clinica.modelo.Enum.*;
import co.edu.uniquindio.clinica.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.clinica.servicios.interfaces.PacienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteServicioImpl implements PacienteServicio {

    private final PacienteRepo pacienteRepo;
    private final CitaRepo citaRepo;
    private final MedicoRepo medicoRepo;
    private final AdministradorRepo administradorRepo;
    private final PQRSRepo pqrsRepo;
    private final EmailServicio emailServicio;
    private final RespuestaAdminRepo respuestaAdminRepo;
    private final AnswerPatRepo answerPatRepo;
    private final DialibreRepo dialibre;
    private final HorarioRepo horarioRepo;

    private boolean estaRepetidaCedula(String cedula) {
        return pacienteRepo.buscarPorCedula(cedula) != null;
    }

    private boolean estaRepetidoCorreo(String correo) {
        return pacienteRepo.buscarPorCorreo(correo) != null;
    }

    @Override
    public int registrarse(RegistroPacienterDTO userDTO) throws Exception {

        Paciente paciente = new Paciente();

        if (estaRepetidaCedula(userDTO.cedula())) {
            throw new Exception("La cédula ya se encuentra registrada");
        }
        if (estaRepetidoCorreo(userDTO.correo())) {
            throw new Exception("El correo ya se encuentra registrado");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode(userDTO.contrasena());

        paciente.setContrasena(passwordEncriptada);
        paciente.setCorreo(userDTO.correo());
        paciente.setNombre(userDTO.nombre());
        paciente.setCedula(userDTO.cedula());
        paciente.setCelular(userDTO.celular());
        paciente.setUrlFoto(userDTO.urlFoto());
        paciente.setCiudad(userDTO.ciudad());
        paciente.setAlergias(userDTO.alergias());
        paciente.setFechaNacimiento(userDTO.fechaNacimiento());
        paciente.setTipoSangre(userDTO.tipoSangre());

        Paciente pacienteNew = pacienteRepo.save(paciente);

        return pacienteNew.getCodigo();
    }

    @Override
    public int editarPerfil(int codigo, DetallePacienteDTO detallePacienteDTO) throws Exception {

        Optional<Paciente> opcional = pacienteRepo.findById(codigo);
        Paciente buscado = opcional.get();

        if (opcional.isEmpty()) {
            throw new Exception("No existe un paciente con el codigo: " + codigo);
        }

        if (estaRepetidoCorreo(detallePacienteDTO.correo()) && (!buscado.getCorreo().equals(detallePacienteDTO.correo()))) {
            throw new Exception("El correo ya se encuentra registrado");
        }
        if (estaRepetidaCedula(detallePacienteDTO.cedula()) && (!buscado.getCedula().equals(detallePacienteDTO.cedula()))) {
            throw new Exception("La cedula ya se encuentra registrada");
        }

        buscado.setCelular(detallePacienteDTO.telefono());
        buscado.setNombre(detallePacienteDTO.nombre());
        buscado.setCedula(detallePacienteDTO.cedula());
        buscado.setUrlFoto(detallePacienteDTO.urlFoto());
        buscado.setEps(detallePacienteDTO.eps());
        buscado.setAlergias(detallePacienteDTO.alergias());
        buscado.setFechaNacimiento(detallePacienteDTO.fechaNacimiento());
        buscado.setTipoSangre(detallePacienteDTO.tipoSangre());
        buscado.setCiudad(detallePacienteDTO.ciudad());
        buscado.setCorreo(detallePacienteDTO.correo());

        pacienteRepo.save(buscado);

        return buscado.getCodigo();
    }

    @Override
    public void eliminarCuenta(int cedula) throws Exception {

        Optional<Paciente> optional = pacienteRepo.findById(cedula);

        if (optional.isEmpty()) {
            throw new Exception("No hay un usuario con la cedula: " + cedula);
        }

        Paciente buscado = optional.get();
        pacienteRepo.delete(buscado);
    }

    @Override
    public int agendarCita(CitaPacienteDTO citaDTO) throws Exception {

        Optional<Medico> medico = medicoRepo.findById(citaDTO.codigoMedico());
        Optional<Paciente> paciente = pacienteRepo.findById(citaDTO.codigoPaciente());

        if (medico.isEmpty()) {
            throw new Exception("No existe el medico con el código " + citaDTO.codigoMedico());
        }
        if (paciente.isEmpty()) {
            throw new Exception("No existe el  paciente con código " + citaDTO.codigoPaciente());
        }
        long numeroCitasActivas = citaRepo.countAllByPacienteIdAndEstadoCita(citaDTO.codigoPaciente(), EstadoCita.PENDIENTE);

        if (numeroCitasActivas >= 3) {
            throw new Exception("El numero de citas ha programar, máximo pueden ser 3");
        }

        Cita citaNueva = new Cita();

        citaNueva.setMotivo(citaDTO.motivo());
        citaNueva.setFechaCreacion(LocalDateTime.now());
        citaNueva.setFechaCita(citaDTO.fecha());
        citaNueva.setMedico(medico.get());
        citaNueva.setPaciente(paciente.get());
        citaNueva.setEstadoCita(EstadoCita.PENDIENTE);

        Cita citaRegistrada = citaRepo.save(citaNueva);

        emailServicio.EnviarEmail(new EmailDTO("Agendamiento de Cita", paciente.get().getCorreo(), "Haz agendado una cita para el " + citaRegistrada.getFechaCita() + " Motivo: " + citaRegistrada.getMotivo() + " con el especialista en " + citaRegistrada.getMedico().getEspecializacion() + " " + citaRegistrada.getMedico().getNombre()));

        emailServicio.EnviarEmail(new EmailDTO("Agendamiento de Cita", medico.get().getCorreo(), "Se ha agendada " + citaRegistrada.getFechaCita() + " Motivo: " + citaRegistrada.getMotivo() + " Nombre del Paciente: " + paciente.get().getNombre()));

        return citaRegistrada.getCodigo();
    }

    @Override
    public int crearPQRS(PqrsPacienteDTO pqrsPacienteDTO) throws Exception {

        List<Administrador> administradorList = administradorRepo.findAll();

        if (administradorList.isEmpty()) {
            throw new Exception("No hay administradores");
        }

        Optional<Cita> opcional = citaRepo.findById(pqrsPacienteDTO.codigoCita());

        if (opcional.isEmpty()) {
            throw new Exception("No existe la cita con el código " + pqrsPacienteDTO.codigoCita());
        }

        List<Pqrs> pqrsList = pqrsRepo.findAllByCita_Paciente_IdAndEstadoPqrsEquals(opcional.get().getPaciente().getCodigo(), EstadoPqrs.NUEVO);

        if (pqrsList.size() >= 2) {
            throw new Exception("Solo puedes tener 2 pqrs por el momento");
        }

        Pqrs pqrsNew = new Pqrs();
        Cita buscado = opcional.get();

        pqrsNew.setEstadoPqrs(EstadoPqrs.NUEVO);
        pqrsNew.setFechaCreacion(LocalDateTime.now());
        pqrsNew.setMotivo(pqrsPacienteDTO.motivo());
        pqrsNew.setCita(buscado);

        Pqrs pqrsRegistrada = pqrsRepo.save(pqrsNew);

        for (Administrador admin : administradorList) {
            emailServicio.EnviarEmail(new EmailDTO("Nuevo PQRS", admin.getCorreo(), pqrsRegistrada.getMotivo()));
        }

        return pqrsRegistrada.getCodigo();

    }

    @Override
    public int responderPQRS(RespuestaPacientePqrsDTO respuestaPacientePqrsDTO) throws Exception {

        Optional<Pqrs> opcionalPqrs = pqrsRepo.findById(respuestaPacientePqrsDTO.codigoPqrs());
        if (opcionalPqrs.isEmpty()) {
            throw new Exception("No existe este pqrs");
        }

        Optional<RespuestaAdmin> respuestaAdmin = respuestaAdminRepo.findById(respuestaPacientePqrsDTO.respuestaAdmin());
        if (respuestaAdmin.isEmpty()) {
            throw new Exception("No existe una respuesta con ese código");
        }

        Optional<Paciente> paciente = pacienteRepo.findById(respuestaPacientePqrsDTO.codigoPaciente());
        if (paciente.isEmpty()) {
            throw new Exception("No existe el paciente");
        }

        if (opcionalPqrs.get().getEstadoPqrs().equals(EstadoPqrs.EN_PROCESO)) {

            Paciente buscadoPaciente = paciente.get();
            RespuestaAdmin answerAdmin = respuestaAdmin.get();
            RespuestaPaciente answerPaci = new RespuestaPaciente();

            answerPaci.setRespuestaAdmin(answerAdmin);
            answerPaci.setFecha(LocalDateTime.now());
            answerPaci.setPqrs(opcionalPqrs.get());
            answerPaci.setMensaje(respuestaPacientePqrsDTO.mensaje());

            answerPaci.setPaciente(buscadoPaciente);

            RespuestaPaciente respuestaPacienteRegistrada = answerPatRepo.save(answerPaci);

            emailServicio.EnviarEmail(new EmailDTO(
                    "Respuesta al paciente",
                    answerAdmin.getAdministrador().getCorreo(),
                    respuestaPacienteRegistrada.getMensaje()));

            return respuestaPacienteRegistrada.getId();

        } else {
            throw new Exception("Su estado es: " + opcionalPqrs.get().getEstadoPqrs() + " por lo tanto no es posible una respuesta");
        }
    }

    @Override
    public List<ItemCitaPacienteDTO> listarCitasPaciente(int codigoPaciente) throws Exception {

        List<Cita> citasPaciente = citaRepo.findByPaciente_Codigo(codigoPaciente);

        if (citasPaciente.isEmpty()) {
            throw new Exception("No hay citas");
        }

        List<ItemCitaPacienteDTO> respuesta = new ArrayList<>();

        for (Cita cita : citasPaciente) {
            respuesta.add(new ItemCitaPacienteDTO(
                    cita.getMotivo(),
                    cita.getFechaCreacion(),
                    cita.getFechaCita(),
                    cita.getEstadoCita(),
                    cita.getMedico().getNombre()));
        }

        return respuesta;
    }

    @Override
    public List<ItemMedicoCitaDTO> filtrarMedicoCita(Especializacion especialidad, LocalDateTime fecha) throws Exception {

        List<Medico> medicosEspecializados = medicoRepo.obtenerMedicoEspecialidad(especialidad);

        if (medicosEspecializados.isEmpty()) {
            throw new Exception("No hay médicos con la Especialidad " + especialidad);
        }

        // Separacion fecha
        DayOfWeek dayWeek = fecha.getDayOfWeek(); // dia de la semana
        int numeroDia = dayWeek.getValue() - 1;
        Dia dia = Dia.values()[numeroDia];

        // Filtro los que no tienen el dia libre
        List<Medico> medicosLibres = new ArrayList<>();

        for (Medico medico : medicosEspecializados) {
            if (dialibre.obtenerDiaLibreFecha(medico.getCodigo(), fecha) == null) {
                medicosLibres.add(medico);
            }
        }

        // Hora de agendamiento de cita
        List<ItemMedicoCitaDTO> listaItemMedicoCitaDTOS = new ArrayList<>();

        for (Medico medico : medicosLibres) {

            Horario horarioMedico = horarioRepo.obtenerHorarioFecha(medico.getCodigo(), dia);
            List<Cita> citasPendientes = citaRepo.obtenerCitasFecha(medico.getCodigo(), fecha);

            LocalDateTime horaInicioCita = horarioMedico.getHoraInicio();
            LocalDateTime horaFin = horarioMedico.getHoraFin();

            // Se evalua la eventualidad de una posible nueva cita
            while (!horaInicioCita.equals(horaFin)) {

                boolean sePuedeAgendar = true;
                //Validamos que ninguna cita cumpla con esa hora
                for (Cita cita : citasPendientes) {
                    if (horaInicioCita.equals(cita.getFechaCita())) {
                        sePuedeAgendar = false;
                        break;
                    }
                }

                if (sePuedeAgendar) {
                    listaItemMedicoCitaDTOS.add(new ItemMedicoCitaDTO(
                            medico.getCodigo(),
                            medico.getNombre(),
                            horaInicioCita));
                }
                //Sumamos 30 minutos que es la duración de una cita
                horaInicioCita = horaInicioCita.plusMinutes(30);
            }
        }


        return listaItemMedicoCitaDTOS;
    }

    @Override
    public List<ItemPqrsDTO> listarPqrsPaciente(int codigoPaciente) throws Exception {

        List<Pqrs> pqrsPacientes = pqrsRepo.findAllByCita_Paciente_Codigo(codigoPaciente);

        if (pqrsPacientes.isEmpty()) {
            throw new Exception("No tienes pqrs registradas");
        }

        List<ItemPqrsDTO> listaItemPqrsDTO = new ArrayList<>();

        for (Pqrs pqrs : pqrsPacientes) {
            listaItemPqrsDTO.add(new ItemPqrsDTO(
                    pqrs.getCodigo(),
                    pqrs.getMotivo(),
                    pqrs.getFechaCreacion(),
                    pqrs.getEstadoPqrs()));
        }

        return listaItemPqrsDTO;

    }

    @Override
    public DetallePacienteDTO verDetallePaciente(int codigo) throws Exception {

        Optional<Paciente> pacienteOptional = pacienteRepo.findById(codigo);

        if (pacienteOptional.isEmpty()) {
            throw new Exception("No existe un paciente con el codigo: " + codigo);
        }

        Paciente buscado = pacienteOptional.get();

        return new DetallePacienteDTO(
                buscado.getCodigo(),
                buscado.getCedula(),
                buscado.getNombre(),
                buscado.getCelular(),
                buscado.getUrlFoto(),
                buscado.getCiudad(),
                buscado.getFechaNacimiento(),
                buscado.getAlergias(),
                buscado.getEps(),
                buscado.getTipoSangre(),
                buscado.getCorreo()

        );
    }

    @Override
    public List<ItemCitaDTO> filtrarCitasPorFecha(FiltrarSearchCitaDTO filtrarSearchCitaDTO) throws Exception {

        Optional<Paciente> pacienteOptional = pacienteRepo.findById(filtrarSearchCitaDTO.codigoPaciente());

        if (pacienteOptional.isEmpty()) {
            throw new Exception("El paciente no existe");
        }

        if (filtrarSearchCitaDTO.fechaCita() == null) {
            throw new Exception("Porfavor ingrese el campo para hacer la busqueda");
        }

        int codigoPaciente = filtrarSearchCitaDTO.codigoPaciente();
        String nombreMedico = filtrarSearchCitaDTO.nombreMedico();
        LocalDateTime fechaCita = filtrarSearchCitaDTO.fechaCita();

        List<ItemCitaDTO> itemCitaDTOList = new ArrayList<>();
        List<Cita> citas;

        if (filtrarSearchCitaDTO.fechaCita() != null) {
            citas = citaRepo.findCitasCompletadasByPacienteAndNombreMedicoAndFechaCita(codigoPaciente, nombreMedico, fechaCita);

            for (Cita cita : citas) {
                ItemCitaDTO itemCitaDTO = new ItemCitaDTO(
                        cita.getCodigo(),
                        cita.getPaciente().getCedula(),
                        cita.getPaciente().getNombre(),
                        cita.getMedico().getNombre(),
                        cita.getEstadoCita(),
                        cita.getFechaCita(),
                        cita.getMotivo()
                );

                itemCitaDTOList.add(itemCitaDTO);
            }
        }
        return itemCitaDTOList;

    }

    @Override
    public void enviarLinkRecuperacion(String correo) throws Exception {
        emailServicio.EnviarEmail(new EmailDTO("Recupera tu cuenta", correo, "link"));
    }


}

