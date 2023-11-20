package co.edu.uniquindio.clinica.test;

import co.edu.uniquindio.clinica.dto.Clinica.MensajeDTO2;
import co.edu.uniquindio.clinica.dto.admin.LoginDTO;
import co.edu.uniquindio.clinica.dto.token.TokenDTO;
import co.edu.uniquindio.clinica.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.clinica.servicios.interfaces.ClinicaServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
@Transactional
public class CinicaServicioTest {

    @Autowired
    ClinicaServicio clinicaServicio;
    @Autowired
    private AutenticacionServicio autenticacionServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPQRSTest() {
        // nicialización de variables
        List<MensajeDTO2> mensajes;
        try {
            // Invocar al método que se va a probar
            mensajes = clinicaServicio.listarPQRS(1);
        } catch (Exception e) {
            // Manejo de excepciones: lanzar una RuntimeException si ocurre un error
            throw new RuntimeException(e);
        }
        // Verificación de resultados usando assertions
        Assertions.assertEquals(6, mensajes.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void cambiarPassword() {

        try {
            // Cambiar la contraseña del usuario con el código 1 a "0000"
            clinicaServicio.cambiarPassword(1, "0000");

            // Intentar realizar el inicio de sesión con las nuevas credenciales
            TokenDTO tokenDTO = autenticacionServicio.login(new LoginDTO("Douglas@gmail.com", "0000"));

            // Verificar que se ha obtenido un token de inicio de sesión no nulo
            Assertions.assertNotNull(tokenDTO);
            System.out.println(tokenDTO);

        } catch (Exception e) {
            // Manejo de excepciones: lanzar una RuntimeException si ocurre un error
            throw new RuntimeException(e);
        }
    }

}