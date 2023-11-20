package co.edu.uniquindio.clinica.dto.Clinica;
public record MensajeDTO<T>(
        boolean error,
        T respuesta)

{
}