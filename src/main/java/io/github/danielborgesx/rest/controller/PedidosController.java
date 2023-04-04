package io.github.danielborgesx.rest.controller;

import io.github.danielborgesx.serivce.PedidoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {

    private PedidoService pedidoService;

    public PedidosController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }


}
