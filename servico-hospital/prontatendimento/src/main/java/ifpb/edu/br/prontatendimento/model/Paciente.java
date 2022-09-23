package ifpb.edu.br.prontatendimento.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_paciente")
public class Paciente extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Integer id;

    @Column(name = "numero_sus")
    private String numero_sus;

    @Column(name = "nome_mae")
    private String nome_mae;

    @OneToOne
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa pessoa;
}
