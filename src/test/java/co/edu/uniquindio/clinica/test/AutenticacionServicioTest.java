package co.edu.uniquindio.clinica.test;

import co.edu.uniquindio.clinica.dto.admin.LoginDTO;
//import co.edu.uniquindio.clinica.dto.;
import co.edu.uniquindio.clinica.dto.token.TokenDTO;
import co.edu.uniquindio.clinica.dto.token.TokenDTO;
import co.edu.uniquindio.clinica.servicios.interfaces.AutenticacionServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


@SpringBootTest
@Transactional
public class AutenticacionServicioTest {

    @Autowired
    AutenticacionServicio autenticacionServicio;

    @Test
    @Sql("classpath:dataset.sql")
    void testLogin() throws Exception {
        String contrasenia = "1234"; // Contraseña válida para la cuenta de prueba
        LoginDTO loginDTO = new LoginDTO("pepito@email.com", contrasenia);

        TokenDTO tokenDTO = autenticacionServicio.login(loginDTO);

        Assertions.assertNotNull(tokenDTO);

    }

}