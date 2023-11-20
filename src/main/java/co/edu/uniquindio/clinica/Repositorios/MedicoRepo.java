package co.edu.uniquindio.clinica.Repositorios;

import co.edu.uniquindio.clinica.modelo.Entidades.Atencion;
import co.edu.uniquindio.clinica.modelo.Entidades.Cuenta;
import co.edu.uniquindio.clinica.modelo.Entidades.Medico;
import co.edu.uniquindio.clinica.modelo.Enum.Especializacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepo extends JpaRepository<Medico, Integer> {

    @Query("select m from Medico m where m.especializacion = :especialidad")
    List<Medico> obtenerMedicoEspecialidad(Especializacion especialidad);

    @Query("select c from Atencion c where c.cita.medico.codigo = :codigoMedico")
    List<Atencion> consultasRealizadas(int codigoMedico);

    Cuenta findByCorreo(String correo);

}