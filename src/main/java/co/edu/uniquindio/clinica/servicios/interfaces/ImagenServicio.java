package co.edu.uniquindio.clinica.servicios.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

public interface ImagenServicio {
    Map subirImagen(MultipartFile imagen) throws Exception;

    Map eliminarImagen(String idImagen) throws Exception;
}
