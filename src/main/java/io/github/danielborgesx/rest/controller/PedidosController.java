package io.github.danielborgesx.rest.controller;

import io.github.danielborgesx.domain.entity.ItemPedido;
import io.github.danielborgesx.domain.entity.Pedido;
import io.github.danielborgesx.serivce.PedidoService;
import io.github.danielborgesx.serivce.dto.InformacaoItemPedidoDTO;
import io.github.danielborgesx.serivce.dto.InformacoesPedidoDTO;
import io.github.danielborgesx.serivce.dto.PedidoDTO;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {

    private final PedidoService pedidoService;

    public PedidosController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoService.salvar(pedidoDTO);
        return pedido.getId();
    }
    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id) {
        return pedidoService.obterPedidoCompleto(id)
                .map(this::converter)
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Pedido não encontrado"));
    }

    private InformacoesPedidoDTO converter (Pedido pedido) {
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCLiente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .informacaoItemPedidoDTOList(converter(pedido.getItensPedidos()))
                .build();
    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens) {
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
       return itens
               .stream()
               .map(item ->
                       InformacaoItemPedidoDTO
                               .builder()
                               .descricaoProduto(item.getProduto().getDescricao())
                               .precoUnitario(item.getProduto().getPreco())
                               .quantidade(item.getQuantidade()).build()
                       ).collect(Collectors.toList());
    }

}
