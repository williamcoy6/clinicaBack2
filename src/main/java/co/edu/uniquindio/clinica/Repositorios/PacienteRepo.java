package co.edu.uniquindio.clinica.Repositorios;

import co.edu.uniquindio.clinica.modelo.Entidades.Atencion;
import co.edu.uniquindio.clinica.modelo.Entidades.Cuenta;
import co.edu.uniquindio.clinica.modelo.Entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepo extends JpaRepository<Paciente, Integer> {

    @Query("select p from Paciente p where p.cedula = :cedula")
    Paciente buscarPorCedula(String cedula);
    @Query("select p from Paciente p where p.correo = :correo")
    Paciente buscarPorCorreo(String correo);
    @Query("select c from Atencion c where c.cita.paciente.codigo =:codigoPaciente")
    List<Atencion> buscarConsultasPaciente(int codigoPaciente);

    Cuenta findByCorreo(String correo);


}

