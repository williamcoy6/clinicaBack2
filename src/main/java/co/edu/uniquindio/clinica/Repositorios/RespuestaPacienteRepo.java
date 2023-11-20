package co.edu.uniquindio.clinica.Repositorios;

import co.edu.uniquindio.clinica.modelo.Entidades.RespuestaAdmin;
import co.edu.uniquindio.clinica.modelo.Entidades.RespuestaPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaPacienteRepo extends JpaRepository<RespuestaAdmin, Integer> {
    List<RespuestaPaciente>findByPqrs_Codigo(int codePqrs);
}
