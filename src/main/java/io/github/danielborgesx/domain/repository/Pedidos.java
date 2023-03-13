package io.github.danielborgesx.domain.repository;

import io.github.danielborgesx.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
}
