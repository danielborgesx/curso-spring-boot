package io.github.danielborgesx.serivce.impl;

import io.github.danielborgesx.domain.repository.Pedidos;
import io.github.danielborgesx.serivce.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private Pedidos pedidos;

    public PedidoServiceImpl(Pedidos pedidos) {
        this.pedidos = pedidos;
    }


}
