package com.angel.security.repository;

import com.angel.security.model.FacturaModel;

import java.util.List;
import java.util.Optional;

public interface IFacturaRepository {

    List<FacturaModel> findAll();
    List<FacturaModel> findByCliente(String idCliente);

    Optional<FacturaModel> findById(String id);

    FacturaModel save(FacturaModel factura);
    FacturaModel update(String id, FacturaModel factura);

    boolean existsCliente(String idCliente);
    boolean existsEstadoFactura(String idEstadoFactura);

}
