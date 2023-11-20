package co.edu.uniquindio.clinica.test;

import co.edu.uniquindio.clinica.dto.Clinica.ItemPqrsDTO;
import co.edu.uniquindio.clinica.dto.medico.ItemCitaDTO;
import co.edu.uniquindio.clinica.dto.paciente.*;
import co.edu.uniquindio.clinica.modelo.Enum.Ciudad;
import co.edu.uniquindio.clinica.modelo.Enum.Especializacion;
import co.edu.uniquindio.clinica.servicios.interfaces.PacienteServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class PacienteServicioTest {

    @Autowired
    private PacienteServicio pacienteServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarTest() throws Exception {

        //Creamos un objeto con los datos del paciente
        RegistroPacienterDTO pacienterDTO = new RegistroPacienterDTO("Douglas", "douglas@gmail.com", "10012176594", "3107371692", "12345", "url_foto", LocalDate.of(2002, 7, 15), Ciudad.BOGOTA, "Polen");

        int codigoPaciente;
        try {
            codigoPaciente = pacienteServicio.registrarse(pacienterDTO); //Guardamos el registro usando el método del servicio
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Comprobamos que sí haya quedado guardado. Si se guardó debe retornar el código (no 0).
        Assertions.assertNotEquals(0, codigoPaciente);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void editarPerfil() throws Exception {
        DetallePacienteDTO enBase = pacienteServicio.verDetallePaciente(1);
        DetallePacienteDTO modificado = new DetallePacienteDTO(enBase.codigo(), enBase.cedula(), enBase.nombre(), enBase.telefono(), enBase.urlFoto(), enBase.ciudad(), enBase.fechaNacimiento(), enBase.alergias(), enBase.eps(), enBase.tipoSangre(), enBase.correo());

        pacienteServicio.editarPerfil(1, modificado);
        DetallePacienteDTO actualizado = pacienteServicio.verDetallePaciente(1);
        Assertions.assertNotEquals(enBase.eps(), actualizado.eps());
        Assertions.assertEquals(modificado.eps(), actualizado.eps());

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarCuentaTest() {

        DetallePacienteDTO detallePacienteDTO;
        try {

            pacienteServicio.eliminarCuenta(1);
            detallePacienteDTO = pacienteServicio.verDetallePaciente(1); // Intenta obtener el detalle del paciente después de eliminar la cuenta
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void filtrarMedicoCita() {

        List<ItemMedicoCitaDTO> medicoCitaDTOList;

        try {
            medicoCitaDTOList = pacienteServicio.filtrarMedicoCita(Especializacion.NEUROLOGIA, LocalDateTime.of(2023, 10, 20, 9, 0));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(4, medicoCitaDTOList.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void verDetallePaciente() throws Exception {

        DetallePacienteDTO paciente = pacienteServicio.verDetallePaciente(1);
        System.out.println(paciente);
        Assertions.assertEquals(4, paciente.codigo());

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void agendarCita() {

        CitaPacienteDTO citaDTO = new CitaPacienteDTO("Ojos rojos", LocalDateTime.of(2023, 10, 5, 12, 0), 4, 1);

        int codigoCita;

        try {
            codigoCita = pacienteServicio.agendarCita(citaDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertNotEquals(0, codigoCita);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearPqrs() {
        PqrsPacienteDTO pqrsPacienteDTO = new PqrsPacienteDTO(1, "Enfermedad");

        int numRadicado;

        try {
            numRadicado = pacienteServicio.crearPQRS(pqrsPacienteDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertNotEquals(0, numRadicado);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPqrsPaciente() {

        List<ItemPqrsDTO> listaPqrsPaciente;

        try {
            listaPqrsPaciente = pacienteServicio.listarPqrsPaciente(1);
            // Agregar cualquier otra verificación necesaria después de la llamada al método
            Assertions.assertEquals(1, listaPqrsPaciente.size());
        } catch (Exception e) {
            // nueva excepción si es necesario
            Assertions.fail("Se produjo una excepción inesperada: " + e.getMessage());
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void responderPqrs() {
        RespuestaPacientePqrsDTO respuestaPacientePqrsDTO = new RespuestaPacientePqrsDTO(1, "Mensaje", 1, 1);

        try {
            pacienteServicio.responderPQRS(respuestaPacientePqrsDTO);
        } catch (Exception e) {
            // nueva excepción si es necesario
            Assertions.fail("Se produjo una excepción inesperada: " + e.getMessage());
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCitasPaciente() {

        List<ItemCitaPacienteDTO> listaCitasPaciente;

        try {
            listaCitasPaciente = pacienteServicio.listarCitasPaciente(1);
            Assertions.assertEquals(1, listaCitasPaciente.size());
        } catch (Exception e) {
            // nueva excepción si es necesario
            Assertions.fail("Se produjo una excepción inesperada: " + e.getMessage());
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void filtrarCitasFecha() {
        try {
            LocalDateTime fechaHora = LocalDateTime.of(2023, 10, 6, 11, 15, 0);
            FiltrarSearchCitaDTO filtroBusquedaCitaDTO = new FiltrarSearchCitaDTO(1, "Dr. Rodriguez", fechaHora);
            List<ItemCitaDTO> citaDTOList = pacienteServicio.filtrarCitasPorFecha(filtroBusquedaCitaDTO);
            Assertions.assertEquals(1, citaDTOList.size());

        } catch (Exception e) {
            // nueva excepción si es necesario
            Assertions.fail("Se produjo una excepción inesperada: " + e.getMessage());
        }
    }

}
