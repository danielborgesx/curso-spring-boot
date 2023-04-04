package io.github.danielborgesx.rest.controller;

import io.github.danielborgesx.domain.entity.Pedido;
import io.github.danielborgesx.serivce.PedidoService;
import io.github.danielborgesx.serivce.dto.PedidoDTO;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {

    private PedidoService pedidoService;

    public PedidosController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoService.salvar(pedidoDTO);
        return pedido.getId();
    }

}
