package co.edu.uniquindio.clinica.modelo.Entidades;

import co.edu.uniquindio.clinica.modelo.Entidades.Cuenta;
import co.edu.uniquindio.clinica.modelo.Enum.Ciudad;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario extends Cuenta implements Serializable {

    @Column(nullable = false, length = 10, unique = true)
    private String cedula;
    @Column(nullable = false, length = 200)
    private String nombre;
    @Column(nullable = false, length = 10)
    private String celular;
    @Column(nullable = false, length = 50)
    private String correo;

    @Column(nullable = false, length = 200)
    private String contrasena;
    @Lob
    @Column(nullable = false)
    private String urlFoto;
    private Ciudad ciudad;


}
