package com.angel.security.service;

import jakarta.validation.constraints.Max;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.angel.security.model.UserModel;
import com.angel.security.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository iUserRepository;


    @Override
    public UserDetails loadUserByUsername(String correo)
            throws UsernameNotFoundException {

        UserModel userModel =
                this.iUserRepository.findByEmail(correo);

        if(userModel == null){
            throw new UsernameNotFoundException("Correo no encontrado");
        }

        return new User(
                userModel.getCorreo(),
                userModel.getPassword(),
                List.of(
                        new SimpleGrantedAuthority(
                                userModel.getNombreRol()
                        )
                )
        );

    }

    public UserModel findByEmail(String correo){
        return this.iUserRepository.findByEmail(correo);
    }

}
