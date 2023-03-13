package io.github.danielborgesx.domain.repository;

import io.github.danielborgesx.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedido extends JpaRepository<ItemPedido, Integer> {
}
