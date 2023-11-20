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
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Horario implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int codigo;
    @Column(nullable = false)
    private LocalDate dia;
    @Column(nullable = false)
    private LocalDateTime horaInicio;
    @Column(nullable = false)
    private LocalDateTime horaFin;

    @ManyToOne
    private Medico medico;

}
