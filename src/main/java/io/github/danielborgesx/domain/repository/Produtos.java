package io.github.danielborgesx.domain.repository;

import io.github.danielborgesx.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {

}
