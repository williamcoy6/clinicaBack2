package co.edu.uniquindio.clinica.test;

import co.edu.uniquindio.clinica.dto.admin.ConsultaDTO;
import co.edu.uniquindio.clinica.dto.medico.DetalleAtencionMedicaDTO;
import co.edu.uniquindio.clinica.dto.medico.DiaLibreDTO;
import co.edu.uniquindio.clinica.dto.medico.RegistroAtencionDTO;
import co.edu.uniquindio.clinica.dto.paciente.MedicamentoDTO;
import co.edu.uniquindio.clinica.servicios.interfaces.MedicoServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MedicoServicioTest {

    @Autowired
    private MedicoServicio medicoServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCitasPendientes() {
        // Inicialización de variables
        List<ConsultaDTO> consultas;
        try {
            // Invocar al método que se va a probar
            consultas = medicoServicio.listarCitasPendientes(1);
        } catch (Exception e) {
            // Manejo de excepciones: lanzar una RuntimeException si ocurre un error
            throw new RuntimeException(e);
        }
        // Verificación de resultados usando assertions
        Assertions.assertEquals(2, consultas.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void atenderCita() {

        List<MedicamentoDTO> medicamentoDTOS = new ArrayList<>();
        medicamentoDTOS.add(new MedicamentoDTO("Loratadina", 3, "uso oral", "Una pasta cada 8 horas"));

        int codigoConsulta;

        // Creación de un objeto AtencionMedicoDTO para simular la información de la atención médica
        RegistroAtencionDTO atencionMedicoDTO = new RegistroAtencionDTO(1, 1, "Alucina", "Debe dejar las drogas", "Mareos", medicamentoDTOS, "...", "deprecion");
        try {
            //  Invocar al método que se va a probar
            codigoConsulta = medicoServicio.atenderCita(atencionMedicoDTO);
        } catch (Exception e) {
            // Manejo de excepciones: lanzar una RuntimeException si ocurre un error
            throw new RuntimeException(e);
        }
        // Verificación de resultados usando assertions
        Assertions.assertNotEquals(0, codigoConsulta);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHistorialAtencionesPaciente() {
        // Inicialización de variables
        List<DetalleAtencionMedicaDTO> listaConsultas;
        try {
            // Invocar al método que se va a probar
            listaConsultas = medicoServicio.listarHistorialAtencionesPaciente(1);
        } catch (Exception e) {
            // Manejo de excepciones: lanzar una RuntimeException si ocurre un error
            throw new RuntimeException(e);
        }
        // Verificación de resultados usando assertions
        Assertions.assertEquals(1, listaConsultas.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void agendarDiaLibre() {
        // Inicialización de variables
        DiaLibreDTO diaLibreDTO = new DiaLibreDTO(1, LocalDateTime.of(2020, 12, 5, 0, 0));
        int codigoDiaLibre;

        try {
            // Invocar al método que se va a probar
            codigoDiaLibre = medicoServicio.agendarDiaLibre(diaLibreDTO);
        } catch (Exception e) {
            // Manejo de excepciones: lanzar una RuntimeException si ocurre un error
            throw new RuntimeException(e);
        }

        // Verificación de resultados usando assertions
        Assertions.assertNotEquals(0, codigoDiaLibre);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCitasRealizadasMedico() {
        List<DetalleAtencionMedicaDTO> listarCitasRealizadas;

        try {
            listarCitasRealizadas = medicoServicio.listarCitasRealizadasMedico(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(1, listarCitasRealizadas.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void verDetalleAtencion() {

        DetalleAtencionMedicaDTO detalleAtencionMedicaDTO;

        try {
            detalleAtencionMedicaDTO = medicoServicio.verDetalleAtencion(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals("Consulta de rutina", detalleAtencionMedicaDTO.toString());
    }


}
