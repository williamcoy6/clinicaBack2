package co.edu.uniquindio.clinica.modelo.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DiaLibre implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int codigo;

    @ManyToOne
    private Medico medico;

    @Column(nullable = false)
    private LocalDate fecha;
}
