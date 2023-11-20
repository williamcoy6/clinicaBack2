package co.edu.uniquindio.clinica.Repositorios;

import co.edu.uniquindio.clinica.modelo.Entidades.Atencion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtencionRepo extends JpaRepository<Atencion, Integer> {
}
