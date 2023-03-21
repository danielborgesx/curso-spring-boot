package io.github.danielborgesx;

import io.github.danielborgesx.domain.entity.Cliente;
import io.github.danielborgesx.domain.entity.Pedido;
import io.github.danielborgesx.domain.repository.Clientes;
import io.github.danielborgesx.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;


@SpringBootApplication
@RestController
public class VendasApplication {
    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes,
                                  @Autowired Pedidos pedidos) {
        return args -> {

            Cliente cliente = new Cliente("Daniel");

            clientes.save(cliente);

            Pedido pedido = new Pedido();

            pedido.setCliente(cliente);
            pedido.setDataPedido(LocalDate.now());
            pedido.setTotal(BigDecimal.valueOf(100));

            pedidos.save(pedido);

            Cliente clienteComPedido = clientes.findClienteFetchPedidos(cliente.getId());
            System.out.println(clienteComPedido);
            System.out.println(cliente.getPedidos());
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
