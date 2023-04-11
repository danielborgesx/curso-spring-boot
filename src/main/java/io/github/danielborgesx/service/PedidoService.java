package io.github.danielborgesx.service;

import io.github.danielborgesx.domain.entity.Pedido;
import io.github.danielborgesx.domain.entity.enums.StatusPedido;
import io.github.danielborgesx.service.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO pedidoDTO);

    Optional<Pedido> obterPedidoCompleto(Integer idPedido);

    void atualizaStatus(Integer id, StatusPedido statusPedido);

}
