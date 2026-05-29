package com.angel.security.repository;

import com.angel.security.mapper.ClienteMapper;
import com.angel.security.model.ClienteModel;

import java.util.List;
import java.util.Optional;

public interface IClienteRepository {

    List<ClienteModel> findAll();

    Optional<ClienteModel> findById(String id);

    ClienteModel save(ClienteModel cliente);

    ClienteModel update(String id, ClienteModel cliente);

    void deleteLogical(String id);

}
