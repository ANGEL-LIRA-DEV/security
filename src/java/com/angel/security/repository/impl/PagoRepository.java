package com.angel.security.repository.impl;

import com.angel.security.model.PagoModel;
import com.angel.security.repository.IPagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PagoRepository implements IPagoRepository  {

    private final JdbcTemplate jdbc;

    private final PagoRowMapper mapper = new PagoRowMapper();

    @Override
    public List<PagoModel> findAll() {

        String sql = """
                SELECT
                ID_PAGO,
                ID_FACTURA,
                MONTO,
                ID_ESTADO_PAGO,
                FECHA_PAGO
                FROM PAGOS
                """;

        return jdbc.query(sql, mapper);

    }

    @Override
    public List<PagoModel> findByFactura(String idFactura) {

        String sql = """
                SELECT
                ID_PAGO,
                ID_FACTURA,
                MONTO,
                ID_ESTADO_PAGO,
                FECHA_PAGO
                FROM PAGOS
                WHERE ID_FACTURA = ?
                """;

        return jdbc.query(sql, mapper, idFactura);

    }

    @Override
    public Optional<PagoModel> findById(String id) {

        String sql = """
                SELECT
                ID_PAGO,
                ID_FACTURA,
                MONTO,
                ID_ESTADO_PAGO,
                FECHA_PAGO
                FROM PAGOS
                WHERE ID_PAGO = ?
                """;

        return jdbc.query(sql, mapper, id)
                .stream()
                .findFirst();

    }

    @Override
    public PagoModel save(PagoModel pago) {

        String sql = """
                INSERT INTO PAGOS
                (
                ID_FACTURA,
                MONTO,
                ID_ESTADO_PAGO
                )
                VALUES(?, ?, ?)
                """;

        jdbc.update(sql,
                pago.getIdFactura(),
                pago.getMonto(),
                pago.getIdEstadoPago()
                );

        return pago;

    }

    @Override
    public PagoModel update(String id, PagoModel pago) {

        String sql = """
                UPDATE PAGOS
                SET
                MONTO = ?,
                ID_ESTADO_PAGO = ?
                WHERE ID_PAGO = ?
                """;

        jdbc.update(sql,
                pago.getMonto(),
                pago.getIdEstadoPago(),
                id);

        pago.setIdPago(id);

        return pago;

    }

    @Override
    public boolean existsFactura(String idFactura) {

        String sql = """
                SELECTO COUNT(1)
                FROM FACTURAS
                WHERE ID_FACTURA = ?
                """;

        Integer count = jdbc.queryForObject(sql, Integer.class, idFactura);
        return count != null && count > 0;

    }

    @Override
    public boolean existsEstadoPago(String idEstadoPago) {

        String sql = """
                SELECT COUNT(1)
                FROM CAT_ESTADO_PAGO
                WHERE ID_ESTADO_PAGO = ?
                """;

        Integer count = jdbc.queryForObject(sql, Integer.class, idEstadoPago);

        return count != null && count > 0;

    }
}
