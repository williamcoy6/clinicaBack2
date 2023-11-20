package co.edu.uniquindio.clinica.Repositorios;

import co.edu.uniquindio.clinica.modelo.Entidades.Horario;
import co.edu.uniquindio.clinica.modelo.Enum.Dia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepo extends JpaRepository<Horario, Integer> {

    @Query("select h from Horario h where h.medico.codigo = :codMedico and h.dia = :dia")
    Horario obtenerHorarioFecha(int codMedico, Dia dia);
}
