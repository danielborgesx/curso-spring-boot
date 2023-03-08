package io.github.danielborgesx;

import io.github.danielborgesx.domain.entity.Cliente;
import io.github.danielborgesx.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@SpringBootApplication
@RestController
public class VendasApplication {
    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes) {
        return args -> {

            clientes.save(new Cliente("Daniel"));
            clientes.save(new Cliente("Andreina"));
            List<Cliente> todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);

            todosClientes.forEach(cliente -> {
                cliente.setNome(cliente.getNome() + " atualizado. ");
                clientes.save(cliente);
            });

            todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);

            clientes.findByNomeLike("Cli").forEach(System.out::println);

            clientes.findAll().forEach(clientes::delete);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
