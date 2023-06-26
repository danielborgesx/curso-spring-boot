package io.github.danielborgesx.domain.repository;

import io.github.danielborgesx.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Clientes extends JpaRepository<Cliente, Integer> {

}
