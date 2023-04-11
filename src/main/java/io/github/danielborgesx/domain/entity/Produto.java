package io.github.danielborgesx.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    @NotEmpty(message = "Campo descrição é obrigatório")
    private String descricao;
    @Column(name = "preco_unitario")
    @NotNull(message = "Não pode ser nulo")
    private BigDecimal preco;

}
