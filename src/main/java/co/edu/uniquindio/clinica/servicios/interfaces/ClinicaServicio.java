package co.edu.uniquindio.clinica.servicios.interfaces;

import co.edu.uniquindio.clinica.dto.Clinica.MensajeDTO2;
import co.edu.uniquindio.clinica.modelo.Enum.Ciudad;
import co.edu.uniquindio.clinica.modelo.Enum.Eps;
import co.edu.uniquindio.clinica.modelo.Enum.Especializacion;
import co.edu.uniquindio.clinica.modelo.Enum.TipoSangre;

import java.util.List;

public interface ClinicaServicio {

    List<MensajeDTO2> listarPQRS(int codigoPqrs) throws Exception;

    void cambiarPassword(int codigoUsuario, String nuevaPassword) throws Exception;

    List<Ciudad> listarCiudades();

   List<Eps> listarEps();

   List<Especializacion>listarEspecializacion();

   List<TipoSangre> listarTipoDeSangre();
}
