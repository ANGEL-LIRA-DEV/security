package com.angel.security.repository.impl;

import com.angel.security.model.FacturaModel;
import com.angel.security.repository.IFacturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FacturaRepository implements IFacturaRepository {

    private final JdbcTemplate jdbc;
    private final FacturaRowMapper mapper = new FacturaRowMapper();

    @Override
    public List<FacturaModel> findAll() {

        String sql = """
                SELECT
                ID_FACTURA,
                ID_CLIENTE,
                MONTO_TOTAL,
                FOLIO,
                ANIO,
                ID_ESTADO_FACTURA,
                FECHA_FACTURA
                FROM FACTURAS
                """;

        return jdbc.query(sql, mapper);

    }

    @Override
    public List<FacturaModel> findByCliente(String idCliente) {

        String sql = """
                SELECT
                ID_FACTURA,
                ID_CLIENTE,
                MONTO_TOTAL,
                FOLIO,
                ANIO,
                ID_ESTADO_FACTURA,
                FECHA_FACTURA
                FROM FACTURAS
                WHERE ID_CLIENTE = ?
                """;

        return jdbc.query(sql, mapper, idCliente);

    }

    @Override
    public Optional<FacturaModel> findById(String id) {

        String sql = """
                SELECT
                ID_FACTURA,
                ID_CLIENTE,
                MONTO_TOTAL,
                FOLIO,
                ANIO,
                ID_ESTADO_FACTURA,
                FECHA_FACTURA
                FROM FACTURAS
                WHERE ID_FACTURA = ?
                """;

        return jdbc.query(sql, mapper, id)
                .stream()
                .findFirst();

    }

    @Override
    public FacturaModel save(FacturaModel factura) {

        String sql = """
                INSERT INTO FACTURAS
                (
                ID_CLIENTE,
                MONTO_TOTAL,
                FOLIO,
                ANIO,
                ID_ESTADO_FACTURA
                )
                VALUES (?, ?, ?, ?, ?)
                """;

        jdbc.update(sql,
                factura.getIdCliente(),
                factura.getMontoTotal(),
                factura.getFolio(),
                factura.getAnio(),
                factura.getIdEstadoFactura()
                );

        return factura;

    }

    @Override
    public FacturaModel update(String id, FacturaModel factura) {

        String sql = """
                UPDATE FACTURAS
                SET
                MONTO_TOTAL = ?,
                ID_ESTADO_FACTURA = ?
                WHERE ID_FACTURA = ?
                """;

        jdbc.update(sql,
                factura.getMontoTotal(),
                factura.getIdEstadoFactura(),
                id);

        factura.setIdFactura(id);

        return factura;

    }

    @Override
    public boolean existsCliente(String idCliente) {

        String sql = """
                SELECT COUNT(1)
                FROM CLIENTES
                WHERE ID_CLIENTE = ?
                AND ESTADO = 1
                """;

        Integer count = jdbc.queryForObject(sql, Integer.class, idCliente);

        return count != null && count > 0;

    }

    @Override
    public boolean existsEstadoFactura(String idEstadoFactura) {

        String sql = """
                SELECT COUNT(1)
                FROM CAT_ESTADO_FACTURA
                WHERE ID_ESTADO_FACTURA = ?
                """;

        Integer count = jdbc.queryForObject(sql, Integer.class, idEstadoFactura);

        return count != null && count > 0;

    }
}
