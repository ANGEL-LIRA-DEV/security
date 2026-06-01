package com.angel.security.repository;

import com.angel.security.model.ClienteModel;
import com.angel.security.repository.impl.ClienteRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ClienteRepository implements IClienteRepository {

    private final JdbcTemplate jdbc;
    private final ClienteRowMapper mapper = new ClienteRowMapper();

    @Override
    public List<ClienteModel> findAll() {
        String sql = "SELECT ID_CLIENTE, NOMBRE, APATERNO, AMATERNO, EMAIL, FECHA_ALTA, ESTADO FROM CLIENTES WHERE ESTADO = 1";
        return jdbc.query(sql, mapper);
    }

    @Override
    public Optional<ClienteModel> findById(String id) {
        String sql = "SELECT ID_CLIENTE, NOMBRE, APATERNO, AMATERNO, EMAIL, FECHA_ALTA, ESTADO FROM CLIENTES WHERE ID_CLIENTE = ?";
        return jdbc.query(sql, mapper, id).stream().findFirst();
    }

    @Override
    public ClienteModel save(ClienteModel cliente) {
        String sql = "INSERT INTO CLIENTES (NOMBRE, APATERNO, AMATERNO, EMAIL, ESTADO) VALUES (?, ?, ?, ?, ?)";
        jdbc.update(
                cliente.getNombre(),
                cliente.getAPaterno(),
                cliente.getAMaterno(),
                cliente.getEmail(),
                cliente.getEstado()
        );
        return cliente;
    }

    @Override
    public ClienteModel update(String id, ClienteModel cliente) {
        String sql = "UPDATE CLIENTES SET NOMBRE = ?, APATERNO = ?, AMATERNO = ?, EMAIL = ?, ESTADO = ? WHERE ID_CLIENTE = ?";
        jdbc.update(sql,
                cliente.getNombre(),
                cliente.getAPaterno(),
                cliente.getAMaterno(),
                cliente.getEmail(),
                cliente.getEstado(),
                id
                );
        cliente.setIdCliente(id);
        return cliente;
    }

    @Override
    public void deleteLogical(String id) {
        String sql = "UPDATE CLIENTES SET ESTADO = 0 WHERE ID_CLIENTE = ?";
        jdbc.update(sql, id);
    }
}
