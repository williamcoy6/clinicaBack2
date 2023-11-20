package co.edu.uniquindio.clinica.servicios.Impl;


import co.edu.uniquindio.clinica.Repositorios.*;
import co.edu.uniquindio.clinica.dto.admin.ConsultaDTO;
import co.edu.uniquindio.clinica.dto.medico.DetalleAtencionMedicaDTO;
import co.edu.uniquindio.clinica.dto.medico.DiaLibreDTO;
import co.edu.uniquindio.clinica.dto.medico.RegistroAtencionDTO;
import co.edu.uniquindio.clinica.modelo.Entidades.Atencion;
import co.edu.uniquindio.clinica.modelo.Entidades.Cita;
import co.edu.uniquindio.clinica.modelo.Entidades.DiaLibre;
import co.edu.uniquindio.clinica.modelo.Entidades.Medico;
import co.edu.uniquindio.clinica.modelo.Enum.EstadoCita;
import co.edu.uniquindio.clinica.servicios.interfaces.MedicoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicoServicioImpl implements MedicoServicio {

    private final MedicoRepo medicoRepo;
    private final PacienteRepo pacienteRepo;
    private final DialibreRepo diaLibreRepo;
    private final CitaRepo citaRepo;
    private final AtencionRepo atencionRepo;

    @Override
    public List<ConsultaDTO> listarCitasPendientes(int codigoMedico) throws Exception {

        List<Cita> citasMedico = citaRepo.findAllByMedicoCodigoAndFechaCitaGreaterThanEqual(codigoMedico, LocalDateTime.now());

        if(citasMedico.isEmpty()){
            throw new Exception("No tienes citas pendientes");
        }

        List<ConsultaDTO> respuesta = new ArrayList<>();

        for(Cita cita: citasMedico){
            respuesta.add(new ConsultaDTO(
                    cita.getCodigo(),
                    cita.getPaciente().getCedula(),
                    cita.getPaciente().getNombre(),
                    cita.getFechaCita(),
                    cita.getMotivo()));
        }

        return respuesta;
    }

    @Override
    public int atenderCita(RegistroAtencionDTO registroAtencionDTO) throws Exception {

        Optional<Cita> cita = citaRepo.findById(registroAtencionDTO.codigoCita());

        if(cita.isEmpty()){
            throw new Exception("No existe una cita con el código" + registroAtencionDTO.codigoCita());
        }else if(cita.get().getFechaCita().isAfter(LocalDate.now().atStartOfDay())){
            throw new Exception("La cita no se puede atender dado que la fecha de atención es: "+cita.get().getFechaCita());
        }

        Atencion consultaNueva = new Atencion();
        Cita buscado = cita.get();

        consultaNueva.setFecha(buscado.getFechaCita());
        consultaNueva.setNotasMedicas(registroAtencionDTO.notasMedicas());
        consultaNueva.setDiagnostico(registroAtencionDTO.diagnostico());
        consultaNueva.setSintomas(registroAtencionDTO.sintomas());
        consultaNueva.setCita(buscado);

        Atencion atencion = atencionRepo.save(consultaNueva);
        citaRepo.save(buscado);
        buscado.setEstadoCita(EstadoCita.FINALIZADA);

        return atencion.getCodigo();

    }

    @Override
    public List<DetalleAtencionMedicaDTO> listarHistorialAtencionesPaciente(int codigoPaciente) throws Exception {

        List<Atencion> atencions = pacienteRepo.buscarConsultasPaciente(codigoPaciente);

        if(atencions.isEmpty()){
            throw new Exception("El paciente no ha recibido atención medica");
        }

        List<DetalleAtencionMedicaDTO> descripcionAtencion = new ArrayList<>();

        for (Atencion atencion : atencions){
            descripcionAtencion.add(new DetalleAtencionMedicaDTO(
                    atencion.getCodigo(),
                    atencion.getCita().getPaciente().getNombre(),
                    atencion.getCita().getMedico().getNombre(),
                    atencion.getCita().getMedico().getEspecializacion(),
                    atencion.getCita().getFechaCita(),
                    atencion.getNotasMedicas(),
                    atencion.getDiagnostico()));
        }
        return descripcionAtencion;
    }

    @Override
    public int agendarDiaLibre(DiaLibreDTO diaLibreDTO) throws Exception {

        Optional<Medico> medico = medicoRepo.findById(diaLibreDTO.codigoMedico());

        if(medico.isEmpty()){
            throw new Exception("No se encuentra registrado el medico con el codigo :" + diaLibreDTO.codigoMedico());
        }

        Optional<DiaLibre> diaLibre = diaLibreRepo.findByMedico_CodigoAndFechaGreaterThanEqual(diaLibreDTO.codigoMedico(), LocalDate.now());

        if(diaLibre.isEmpty()){
            throw new Exception((diaLibre.get().getFecha() + ": Es tu dia libre"));
        }

        List<Cita>citas = citaRepo.obtenerCitasFecha(diaLibreDTO.codigoMedico(), diaLibreDTO.fecha());

        if(!citas.isEmpty()){
            throw new Exception("No es posible agendar este dia, tienes citas pendientes");
        }

        Medico medico1 = medico.get();
        DiaLibre dialibre = new DiaLibre();

        dialibre.setMedico(medico1);
        dialibre.setFecha(dialibre.getFecha());
        DiaLibre diaLibreGuardado = diaLibreRepo.save(dialibre);

        return diaLibreGuardado.getCodigo();
    }

    @Override
    public List<DetalleAtencionMedicaDTO> listarCitasRealizadasMedico(int codigoMedico) throws Exception {

        List<Atencion> atencions = medicoRepo.consultasRealizadas(codigoMedico);

        if(atencions.isEmpty()){
            throw new Exception("El medico no ha realizado consultas");
        }

        List<DetalleAtencionMedicaDTO>detalleAtencionMedicaDTOs = new ArrayList<>();

        for(Atencion atencion: atencions ){
            detalleAtencionMedicaDTOs.add(
                    new DetalleAtencionMedicaDTO(
                    atencion.getCodigo(),
                    atencion.getCita().getPaciente().getNombre(),
                    atencion.getCita().getMedico().getNombre(),
                    atencion.getCita().getMedico().getEspecializacion(),
                    atencion.getCita().getFechaCita(),
                    atencion.getNotasMedicas(),
                    atencion.getDiagnostico()));
        }

        return detalleAtencionMedicaDTOs;
    }

    @Override
    public DetalleAtencionMedicaDTO verDetalleAtencion(int codigoCita) throws Exception {

        Optional<Atencion>atencion = atencionRepo.findById(codigoCita);

        if(atencion.isEmpty()){
            throw new Exception("No se encuentra la cita: "+codigoCita  );
        }

        Atencion atencionBuscada = atencion.get();

        return new DetalleAtencionMedicaDTO(
                atencionBuscada.getCodigo(),
                atencionBuscada.getCita().getPaciente().getNombre(),
                atencionBuscada.getCita().getMedico().getNombre(),
                atencionBuscada.getCita().getMedico().getEspecializacion(),
                atencionBuscada.getCita().getFechaCita(),
                atencionBuscada.getNotasMedicas(),
                atencionBuscada.getDiagnostico());
    }

}
