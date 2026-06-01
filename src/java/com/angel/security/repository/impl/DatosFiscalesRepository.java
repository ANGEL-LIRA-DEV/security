package com.angel.security.repository.impl;

import com.angel.security.model.DatosFiscalesModel;
import com.angel.security.repository.IDatosFiscalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DatosFiscalesRepository implements IDatosFiscalesRepository {

    private final JdbcTemplate jdbc;
    private final DatosFiscalesRowMapper mapper = new DatosFiscalesRowMapper();

    @Override
    public List<DatosFiscalesModel> findAl() {

        String sql = "SELECT * FROM DATOS_FISCALES WHERE STATUS = 1";

        return jdbc.query(sql, mapper);

    }

    @Override
    public Optional<DatosFiscalesModel> findById(String id) {

        String sql = "SELECT * FROM DATOS_FISCALES WHERE ID_DATOSFISC = ?";

        return jdbc.query(sql, mapper, id)
                .stream()
                .findFirst();

    }

    @Override
    public Optional<DatosFiscalesModel> findByRfc(String rfc) {

        String sql = "SELECT * FROM DATOS_FISCALES WHERE RFC = ?";

        return jdbc.query(sql, mapper, rfc)
                .stream()
                .findFirst();

    }

    @Override
    public DatosFiscalesModel save(DatosFiscalesModel datos) {

        String sql = """
                INSERT INTO DATOS_FISCALES
                (
                ID_CLIENTE,
                RAZON_SOCIAL,
                RFC,
                CODIGO_POSTAL,
                ID_TIPO_CLIENTE,
                USO_CFDI,
                EMAIL,
                D_ESTADO,
                D_CIUDAD,
                D_COLONIA,
                D_ZONA,
                STATUS
                )
                VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        jdbc.update(sql,
                datos.getIdCliente(),
                datos.getRazonSocial(),
                datos.getRfc(),
                datos.getCodigoPostal(),
                datos.getIdTipoCliente(),
                datos.getUsoCfdi(),
                datos.getEmail(),
                datos.getDEstado(),
                datos.getDCiudad(),
                datos.getDColonia(),
                datos.getDZona(),
                datos.getStatus()
        );

        return datos;

    }

    @Override
    public DatosFiscalesModel update(String id, DatosFiscalesModel datos) {

        String sql = """
                UPDATE DATOS_FISCALES
                SET
                RAZON_SOCIAL = ?,
                CODIGO_POSTAL = ?,
                ID_TIPO_CLIENTE = ?,
                USO_CFDI = ?,
                EMAIL = ?,
                D_ESTADO = ?,
                D_CIUDAD = ?,
                D_COLONIA = ?,
                D_ZONA = ?
                WHERE ID_DATOSFISC = ?
                """;

        jdbc.update(sql,
                datos.getRazonSocial(),
                datos.getCodigoPostal(),
                datos.getIdTipoCliente(),
                datos.getUsoCfdi(),
                datos.getEmail(),
                datos.getDEstado(),
                datos.getDCiudad(),
                datos.getDColonia(),
                datos.getDZona(),
                id
                );

        datos.setIdDatosfisc(id);

        return datos;

    }

    @Override
    public void deleteLogical(String id) {

        String sql = """
                UPDATE DATOS_FISCALES
                SET STATUS = 0
                WHERE ID_DATOSFISC = ?
                """;

        jdbc.update(sql, id);

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
    public boolean existsTipoCliente(String idTipoCliente) {

        String sql = """
                SELECT COUNT(1)
                FROM CAT_TIPO_CLIENTE
                WHERE ID_TIPO_CLIENTE = ?
                """;

        Integer count = jdbc.queryForObject(sql, Integer.class, idTipoCliente);

        return count != null && count > 0;

    }
}
