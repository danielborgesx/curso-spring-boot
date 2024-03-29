package io.github.danielborgesx.domain.entity;

import io.github.danielborgesx.domain.entity.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @Column(name = "data_pedido")
    private LocalDate dataPedido;
    @Column
    private BigDecimal total;
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itensPedidos;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido statusPedido;

}
