package io.github.danielborgesx.serivce;

import io.github.danielborgesx.domain.entity.Pedido;
import io.github.danielborgesx.serivce.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO pedidoDTO);

}
