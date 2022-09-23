package ifpb.edu.br.prontatendimento.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_endereco")
public class Endereco {

    @Id
    @Column(name = "id_endereco")
    private String id;

    @Column(name = "rua_endereco")
    private String rua;

    @Column(name = "numero_endereco")
    private String numero;

    @Column(name = "bairro_endereco")
    private String bairro;

    @Column(name = "cidade_endereco")
    private String cidade;

    @Column(name = "estado_endereco")
    private String estado;

    @OneToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

}
