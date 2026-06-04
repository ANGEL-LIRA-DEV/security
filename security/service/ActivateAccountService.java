package com.angel.security.service;

import com.angel.security.dto.ActivateAccountDto;
import com.angel.security.repository.AuthRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ActivateAccountService {

    @Autowired
    private AuthRegisterRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void activateAccount(
            ActivateAccountDto request){

        if(!request.getPassword()
                .equals(request.getConfirmPassword())){

            throw new RuntimeException(
                    "Las contraseñas no coinciden"
            );

        }

        String idPersona =
                repository.findPersonaByValidToken(
                        request.getToken()
                );

        if(idPersona == null){
            throw new RuntimeException(
                    "Token inválido o expirado"
            );
        }

        String hash =
                passwordEncoder.encode(
                        request.getPassword()
                );

        repository.createUser(idPersona, hash);
        repository.verifyPersona(idPersona);
        repository.markTokenAsUsed(
                request.getToken()
        );


    }

}
