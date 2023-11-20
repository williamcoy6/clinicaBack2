package co.edu.uniquindio.clinica.servicios.Impl;

import com.cloudinary.Cloudinary;

import co.edu.uniquindio.clinica.servicios.interfaces.ImagenServicio;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImgenServicioImpl implements ImagenServicio {
    private final Cloudinary cloudinary; // Por definir
    public ImgenServicioImpl(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "djs3rxuoe");
        config.put("api_key", "755364532934147");
        config.put("api_secret", "X44sjp-ebqOxbjHarY7MriZfg3k");
        cloudinary = new Cloudinary(config);
    }
    @Override
    public Map subirImagen(MultipartFile imagen) throws Exception {

        File file = convertir(imagen);
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", "uniquindio/proyecto/fotos"));
    }
    @Override
    public Map eliminarImagen(String idImagen) throws Exception {
        return cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());
    }
    private File convertir(MultipartFile imagen) throws IOException {
        File file = File.createTempFile(imagen.getOriginalFilename(), null);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getBytes());
        fos.close();
        return file;
    }


}
