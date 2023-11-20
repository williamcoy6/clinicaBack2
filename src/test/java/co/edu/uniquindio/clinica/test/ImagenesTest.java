package co.edu.uniquindio.clinica.test;

import co.edu.uniquindio.clinica.servicios.interfaces.ImagenServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class ImagenesTest {

    @Autowired
    private ImagenServicio imagenesServicio;

    @Test
    public void subirImagen() {
    }
    @Test
    public void eliminarImagen() {
    }
}