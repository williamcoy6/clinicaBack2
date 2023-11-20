package co.edu.uniquindio.clinica.servicios.interfaces;

import co.edu.uniquindio.clinica.dto.Clinica.ItemPqrsDTO;
import co.edu.uniquindio.clinica.dto.medico.ItemCitaDTO;
import co.edu.uniquindio.clinica.dto.paciente.*;
import co.edu.uniquindio.clinica.modelo.Enum.Especializacion;

import java.time.LocalDateTime;
import java.util.List;

public interface PacienteServicio {

    int registrarse(RegistroPacienterDTO registroPacienteDTO) throws Exception;

    int editarPerfil(int codigo, DetallePacienteDTO detallePacienteDTO) throws Exception;

    void eliminarCuenta(int codigoPaciente) throws Exception;

    void enviarLinkRecuperacion(String correo) throws Exception;

    int agendarCita(CitaPacienteDTO citaDTO) throws Exception;

    int crearPQRS(PqrsPacienteDTO pqrsPacienteDTO) throws Exception;

    List<ItemPqrsDTO> listarPqrsPaciente(int idPaciente) throws Exception;

    int responderPQRS(RespuestaPacientePqrsDTO respuestaPacientePqrsDTO) throws Exception;

    List<ItemCitaPacienteDTO> listarCitasPaciente(int codigoPaciente) throws Exception;

    List<ItemCitaDTO> filtrarCitasPorFecha(FiltrarSearchCitaDTO filtrarSearchCitaDTO) throws Exception;

    List<ItemMedicoCitaDTO> filtrarMedicoCita(Especializacion especialidad, LocalDateTime fecha) throws Exception;

    DetallePacienteDTO verDetallePaciente(int codigo) throws Exception;
}
