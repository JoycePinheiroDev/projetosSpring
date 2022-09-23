package ifpb.edu.br.prontatendimento.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_protuario")
public class Prontuario {

    @Id
    @Column(name = "id_protuario")
    private Integer prontuario;

    @Column(name = "sintomas")
    private String sintomas;

    @Column(name = "situacao")
    private String situacao;

    @Column(name = "pressao_arterial")
    private String pressao_arterial;

    @Column(name = "temperatura")
    private String temperatura;

    @Column(name = "alergia")
    private String alergia;

    @OneToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @OneToOne
    @JoinColumn(name = "id_enfermeiro", nullable = false)
    private Enfermeiro enfermeiro;

}
