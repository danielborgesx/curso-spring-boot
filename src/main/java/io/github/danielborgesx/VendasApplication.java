package io.github.danielborgesx;

import io.github.danielborgesx.domain.entity.Cliente;
import io.github.danielborgesx.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@SpringBootApplication
@RestController
public class VendasApplication {

    public CommandLineRunner init (@Autowired Clientes clientes) {
        return args -> {

            clientes.salvarCliente(new Cliente("Daniel"));
            clientes.salvarCliente(new Cliente("Andreina"));
            List<Cliente> todosClientes = clientes.obterTodosOsClientes();
            todosClientes.forEach(System.out::println);
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
