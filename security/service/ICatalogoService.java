package com.angel.security.service;

import com.angel.security.model.CatalogoModel;

import java.util.List;

public interface ICatalogoService {

    List<CatalogoModel> obtenerCatalogo(String tipo);
    List<CatalogoModel> obtenerCiudadesPorEstado(String uuidEstado);

}
