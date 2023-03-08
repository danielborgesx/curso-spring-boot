package io.github.danielborgesx.domain.repository;

import io.github.danielborgesx.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {

    private static final String INSERT = "insert into cliente (nome) values (?) ";
    private static final String SELECT_ALL = "select * from cliente";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void salvarCliente(Cliente cliente) {
        jdbcTemplate.update(INSERT, cliente.getNome());
    }

    public List<Cliente> obterTodosOsClientes() {
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Cliente(rs.getString("nome"));
            }
        });
    }
}
