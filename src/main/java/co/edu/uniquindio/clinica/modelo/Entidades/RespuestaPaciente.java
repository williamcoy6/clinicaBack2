package co.edu.uniquindio.clinica.modelo.Entidades;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RespuestaPaciente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(nullable = false)
    @Lob
    private String mensaje;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Paciente paciente;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Pqrs pqrs;

    @JoinColumn(nullable = false)
    @OneToOne
    private RespuestaAdmin respuestaAdmin;
}
