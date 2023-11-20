package co.edu.uniquindio.clinica.modelo.Entidades;

import co.edu.uniquindio.clinica.modelo.Enum.Eps;
import co.edu.uniquindio.clinica.modelo.Enum.TipoSangre;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Paciente extends Usuario implements Serializable {

    @Column(nullable = false)
    private LocalDate fechaNacimiento;
    @Lob
    @Column(nullable = false)
    private String alergias;
    private Eps eps;
    @Column(nullable = false)
    private TipoSangre tipoSangre;

    @OneToMany(mappedBy = "paciente")
    private List<Cita> citas;
}
