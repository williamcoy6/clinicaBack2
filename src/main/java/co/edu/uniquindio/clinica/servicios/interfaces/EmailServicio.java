package co.edu.uniquindio.clinica.servicios.interfaces;

import co.edu.uniquindio.clinica.dto.Cita.EmailDTO;

public interface EmailServicio {
    void EnviarEmail(EmailDTO emailDTO) throws Exception;
}
