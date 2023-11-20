package co.edu.uniquindio.clinica.Repositorios;

import co.edu.uniquindio.clinica.modelo.Entidades.RespuestaAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaAdminRepo extends JpaRepository<RespuestaAdmin, Integer> {
    List<RespuestaAdmin> findAllByPqrs_Codigo(int codigoPqrs);
}
