package co.edu.uniquindio.clinica.servicios.Impl;

import co.edu.uniquindio.clinica.Repositorios.*;
import co.edu.uniquindio.clinica.dto.Cita.CitaDTOAdmin;
import co.edu.uniquindio.clinica.dto.Cita.EmailDTO;
import co.edu.uniquindio.clinica.dto.PQRS.InfoPQRSDTO;
import co.edu.uniquindio.clinica.dto.PQRS.ItemPqrsAdminDTO;
import co.edu.uniquindio.clinica.dto.admin.*;
import co.edu.uniquindio.clinica.modelo.Entidades.*;
import co.edu.uniquindio.clinica.modelo.Enum.EstadoMedico;
import co.edu.uniquindio.clinica.modelo.Enum.EstadoPqrs;
import co.edu.uniquindio.clinica.servicios.interfaces.AdministradorServicio;
import co.edu.uniquindio.clinica.servicios.interfaces.EmailServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class    AdministradorServicioImpl implements AdministradorServicio {

    private final MedicoRepo medicoRepo;
    private final PQRSRepo pqrsRepo;
    private final CitaRepo citaRepo;
    private final AdministradorRepo adminRepo;
    private final RespuestaAdminRepo respuestaAdminRepo;
    private final EmailServicio emailServicio;


    @Override
    public int crearMedico(RegistroMedicoDTO medicoDTO) throws Exception {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode(medicoDTO.password());

        Medico medico = new Medico();

        medico.setNombre(medicoDTO.nombre());
        medico.setCedula(medicoDTO.cedula() );
        medico.setCorreo(medicoDTO.correo());
        medico.setCelular(medicoDTO.celular());
        medico.setContrasena(passwordEncriptada);
        medico.setEspecializacion(medicoDTO.especializacion());
        medico.setUrlFoto(medicoDTO.urlFoto());
        medico.setCiudad(medicoDTO.cuidad());
        medico.setEstadoMedico(medicoDTO.estadoMedico());

        Medico medicoNuevo = medicoRepo.save(medico);

        return medicoNuevo.getCodigo();
    }

    @Override
    public int actualizarMedico(DetalleMedicoDTO medicoDTO) throws Exception {

        Optional<Medico> opcional = medicoRepo.findById(medicoDTO.codigo());

        if( opcional.isEmpty() ){
            throw new Exception("No existe un médico con el código "+medicoDTO.codigo());
        }

        Medico buscado = opcional.get();

        buscado.setNombre(medicoDTO.nombre());
        buscado.setCedula(medicoDTO.cedula() );
        buscado.setCorreo(medicoDTO.correo());
        buscado.setCelular(medicoDTO.celular());
        buscado.setEspecializacion(medicoDTO.especializacion());
        buscado.setUrlFoto(medicoDTO.urlFoto());
        buscado.setCiudad(medicoDTO.cuidad());
        buscado.setHoraInicio(medicoDTO.horaInicio());
        buscado.setHoraFin(medicoDTO.horaFin());

        medicoRepo.save(buscado);

        return buscado.getCodigo();
    }

    @Override
    public void eliminarMedico(int codigo) throws Exception {

        Optional<Medico> optional = medicoRepo.findById(codigo);

        if(optional.isEmpty()){
            throw new Exception ("No hay medico con el codigo: "+ codigo);
        }

        Medico buscado = optional.get();
        buscado.setEstadoMedico(EstadoMedico.INACTIVO);
        medicoRepo.save(buscado);

    }

    @Override
    public List<ItemMedicoDTO> listarMedicos() throws Exception {

        List<Medico> medicos = medicoRepo.findAll();

        if(medicos.isEmpty()){
            throw new Exception("No hay registro de medicos");
        }

        List<ItemMedicoDTO> respuesta = new ArrayList<>();

        for(Medico m: medicos){
            respuesta.add(new ItemMedicoDTO(
                    m.getCodigo(),
                    m.getCedula(),
                    m.getNombre(),
                    m.getUrlFoto(),
                    m.getEspecializacion()));
        }

        return respuesta;
    }

    @Override
    public DetalleMedicoDTO obtenerMedico(int codigo) throws Exception {

        Optional<Medico> opcional =medicoRepo.findById(codigo);

        if( opcional.isEmpty() ){
            throw new Exception("No existe un médico con el código "+codigo);
        }

        Medico buscado = opcional.get();

        return new DetalleMedicoDTO(
                buscado.getCodigo(),
                buscado.getNombre(),
                buscado.getCorreo(),
                buscado.getCedula(),
                buscado.getCelular(),
                buscado.getEspecializacion(),
                buscado.getHoraInicio(),
                buscado.getHoraFin(),
                buscado.getUrlFoto(),
                buscado.getCiudad(),
                new ArrayList<>()
        );
    }

    @Override
    public List<ItemPqrsAdminDTO> listarPQRS() throws Exception {

        List<Pqrs> listaPqrs = pqrsRepo.findAll();//select * from pqrs
        List<ItemPqrsAdminDTO> respuesta = new ArrayList<>();

        for( Pqrs p : listaPqrs ){
            respuesta.add(new ItemPqrsAdminDTO(
                    p.getCodigo(),
                    p.getEstadoPqrs(),
                    p.getFechaCreacion(),
                    p.getCita().getPaciente().getNombre()
            ));
        }
        return respuesta;
    }

    @Override
    public int responderPQRS(RespuestaAdminDTO respuestaAdminDTO) throws Exception {

        Optional<Pqrs> optional = pqrsRepo.findById(respuestaAdminDTO.codigoPqrs());

        if( optional.isEmpty() ){
            throw new Exception("El código "+respuestaAdminDTO.codigoPqrs()+" no existe PQRS");
        }

        Optional<Administrador> optionalCuenta = adminRepo.findById(respuestaAdminDTO.codigoAdmin());

        if( optionalCuenta.isEmpty() ){
            throw new Exception("El código "+respuestaAdminDTO.codigoAdmin()+" no existe");
        }

        Pqrs pqrs = optional.get();
        Administrador busquedaAdmin = optionalCuenta.get();
        RespuestaAdmin respuestaAdmin = new RespuestaAdmin();

        respuestaAdmin.setAdministrador(busquedaAdmin);
        respuestaAdmin.setPqrs(pqrs);
        respuestaAdmin.setFecha(LocalDateTime.now());
        respuestaAdmin.setMensaje(respuestaAdminDTO.mensaje());

        RespuestaAdmin respuestaAdminNuevo = respuestaAdminRepo.save(respuestaAdmin);
        pqrs.setEstadoPqrs(EstadoPqrs.EN_PROCESO);
        pqrsRepo.save(pqrs);

        emailServicio.EnviarEmail(new EmailDTO("Administrador", pqrs.getCita().getPaciente().getCorreo(), respuestaAdminNuevo.getMensaje()));

        return respuestaAdminNuevo.getId();
    }

    @Override
    public InfoPQRSDTO verDetallePQRS(int codigo) throws Exception {

        Optional<Pqrs> optional = pqrsRepo.findById(codigo);

        if( optional.isEmpty() ){
            throw new Exception("El código " + codigo + " no está asociado a ningún PQRS");
        }

        Pqrs p = optional.get();

        return  new InfoPQRSDTO(
                p.getCodigo(),
                p.getEstadoPqrs(),
                p.getMotivo(),
                p.getCita().getPaciente().getNombre(),
                p.getCita().getMedico().getNombre(),
                p.getCita().getMedico().getEspecializacion(),
                p.getFechaCreacion(),
                new ArrayList<>());
    }

    @Override
    public void cambiarEstadoPQRS(int codigoPQRS, EstadoPqrs estadoPqrs) throws Exception {

        Optional<Pqrs> opcional = pqrsRepo.findById(codigoPQRS);

        if( opcional.isEmpty() ){
            throw new Exception("El código "+codigoPQRS+" no está asociado a ningún PQRS");
        }

        Pqrs pqrs = opcional.get();
        pqrs.setEstadoPqrs(estadoPqrs);

        pqrsRepo.save(pqrs);

    }

    @Override
    public List<CitaDTOAdmin> listarCitas() throws Exception {

        List<Cita> listaCitas = citaRepo.findAll();//select * from pqrs
        List<CitaDTOAdmin> respuesta = new ArrayList<>();

        for( Cita c : listaCitas ){

            respuesta.add( new CitaDTOAdmin(
                    c.getCodigo(),
                    c.getPaciente().getCedula(),
                    c.getPaciente().getNombre(),
                    c.getMedico().getNombre(),
                    c.getFechaCita(),
                    c.getMedico().getEspecializacion(),
                    c.getEstadoCita()

            ));
        }
        return respuesta;
    }
}


