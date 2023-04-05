package io.github.danielborgesx.serivce.impl;

import io.github.danielborgesx.domain.entity.Cliente;
import io.github.danielborgesx.domain.entity.ItemPedido;
import io.github.danielborgesx.domain.entity.Pedido;
import io.github.danielborgesx.domain.entity.Produto;
import io.github.danielborgesx.domain.repository.Clientes;
import io.github.danielborgesx.domain.repository.ItensPedido;
import io.github.danielborgesx.domain.repository.Pedidos;
import io.github.danielborgesx.domain.repository.Produtos;
import io.github.danielborgesx.exception.RegraNegocioException;
import io.github.danielborgesx.serivce.PedidoService;
import io.github.danielborgesx.serivce.dto.ItemPedidoDTO;
import io.github.danielborgesx.serivce.dto.PedidoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos pedidos;
    private final Clientes clientes;
    private final Produtos produtos;
    private final ItensPedido itensPedido;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDTO) {
        Integer idCliente = pedidoDTO.getCliente();
        Cliente cliente = clientes.findById(idCliente).orElseThrow(() -> new RegraNegocioException("Código de cliente inválido!!"));

        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemPedido = converterItems(pedido, pedidoDTO.getItems());
        pedidos.save(pedido);
        itensPedido.saveAll(itemPedido);
        pedido.setItensPedidos(itemPedido);

        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um produto sem items.");
        }

        return items
                .stream()
                .map(itemPedidoDTO -> {
                    Integer idProduto = itemPedidoDTO.getProduto();
                    Produto produto = produtos
                                        .findById(idProduto)
                                        .orElseThrow(() ->
                                            new RegraNegocioException("Código de Produto inválido: " + idProduto));


                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;

                }).collect(Collectors.toList());
    }
}
