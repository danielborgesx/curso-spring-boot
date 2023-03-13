package io.github.danielborgesx.domain.repository;

import io.github.danielborgesx.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Clientes extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByNomeLike(String nome);

    @Query(" select cliente from Cliente cliente left join fetch cliente.pedidos where cliente.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);
}
