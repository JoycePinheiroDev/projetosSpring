package ifpb.edu.br.prontatendimento.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_enfermeiro")
public class Enfermeiro extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Integer id;

    @Column(name = "coren")
    private String coren;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Pessoa pessoa;

}
