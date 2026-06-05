package com.angel.security.repository;

import com.angel.security.model.CatalogoModel;

import java.util.List;

public interface ICatalogoRepository {

    List<CatalogoModel> obtenerCatalogo(
            String tabla,
            String columnaId,
            String columnaDescripcion
    );

    List<CatalogoModel> obtenerCiudadesPorEstado(String uuidEstado);

}
