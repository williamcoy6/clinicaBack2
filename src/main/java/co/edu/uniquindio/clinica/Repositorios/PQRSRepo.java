package co.edu.uniquindio.clinica.Repositorios;

import co.edu.uniquindio.clinica.modelo.Entidades.Pqrs;
import co.edu.uniquindio.clinica.modelo.Enum.EstadoPqrs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PQRSRepo extends JpaRepository<Pqrs, Integer> {

    List<Pqrs> findAllByCita_Paciente_Codigo(int codePaciente);

    @Query("select p from Pqrs p where p.cita.paciente.cedula = :codePaciente and (p.estadoPqrs =:estadoPqrs1 or p.estadoPqrs =:estadoPqrs2)")
    List<Pqrs> findAllByCita_Paciente_IdAndEstadoPqrsEquals(int codePaciente, EstadoPqrs estadoPqrs);

}