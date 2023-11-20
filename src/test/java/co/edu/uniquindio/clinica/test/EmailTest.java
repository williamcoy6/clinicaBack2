package co.edu.uniquindio.clinica.test;


import co.edu.uniquindio.clinica.dto.Cita.EmailDTO;
import co.edu.uniquindio.clinica.servicios.Impl.EmailServiceImpl;
import co.edu.uniquindio.clinica.servicios.interfaces.EmailServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class EmailTest {


    @Autowired
    private EmailServiceImpl emailServicio;

    @Test
    public void enviarEmail() throws Exception {
        emailServicio.EnviarEmail(new EmailDTO("Test", "abcd@gmail.com", "<b>Email</b>/><p>mensaje de prueba</p>"));
    }

}