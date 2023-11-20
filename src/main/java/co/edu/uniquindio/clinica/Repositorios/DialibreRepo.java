package co.edu.uniquindio.clinica.Repositorios;

import co.edu.uniquindio.clinica.modelo.Entidades.DiaLibre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface DialibreRepo extends JpaRepository<DiaLibre, Integer> {
    @Query("select d from DiaLibre d where d.medico.codigo = :codigoMedico and d.fecha = :fecha")
    DiaLibre obtenerDiaLibreFecha(int codigoMedico, LocalDateTime fecha);
    Optional<DiaLibre> findByMedico_CodigoAndFechaGreaterThanEqual(int codigoMedico, LocalDate fecha);

}
