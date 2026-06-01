package com.angel.security.repository;

import com.angel.security.model.DatosFiscalesModel;

import java.util.List;
import java.util.Optional;

public interface IDatosFiscalesRepository {

    List<DatosFiscalesModel> findAl();

    Optional<DatosFiscalesModel> findById(String id);
    Optional<DatosFiscalesModel> findByRfc(String rfc);

    DatosFiscalesModel save(DatosFiscalesModel datos);
    DatosFiscalesModel update(String id, DatosFiscalesModel datos);

    void deleteLogical(String id);
    boolean existsCliente(String idCliente);
    boolean existsTipoCliente(String idTipoCliente);

}
