package com.angel.security.controller;

import com.angel.security.dto.*;
import com.angel.security.exception.ResourceNotFoundException;
import com.angel.security.model.UserModel;
import com.angel.security.repository.AuthRegisterRepository;
import com.angel.security.repository.impl.UserRepository;
import com.angel.security.service.ActivateAccountService;
import com.angel.security.service.JwtUtilService;
import com.angel.security.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private ActivateAccountService activateAccountService;

    @Autowired
    private AuthRegisterRepository authRegisterRepository;

    @PostMapping("/login")
    public ResponseEntity<?> auth(
            @RequestBody AuthRequestDto authRequestDto) {

        try{

            authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      authRequestDto.getEmail(),
                      authRequestDto.getPassword()
              )
            );

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(
                            authRequestDto.getEmail());

            UserModel userModel =
                    userRepository.findByEmail(
                            authRequestDto.getEmail());

            String jwt = jwtUtilService.generateToken(
              userDetails,
              userModel.getNombreRol()
            );

            String refreshToken =
                    jwtUtilService.generateRefreshToken(
                      userDetails,
                      userModel.getNombreRol()
                    );

            AuthResponseDto response = new AuthResponseDto();

            response.setToken(jwt);
            response.setRefresToken(refreshToken);
            response.setSuccess(true);

            return ResponseEntity.ok(response);

        } catch (Exception e){

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Error authentication::: " + e.getMessage());

        }

    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(
            @RequestBody Map<String, String> request) {

        String refreshToken = request.get("refreshToken");

        try{

            String correo =
                    jwtUtilService.extractUsername(refreshToken);

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(correo);

            UserModel userModel =
                    userRepository.findByEmail(correo);

            if(userModel == null){

                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Usuario no encontrado");

            }

            if(jwtUtilService.validarToken(
                    refreshToken,
                    userDetails)){

                String newJwt =
                        jwtUtilService.generateToken(
                                userDetails,
                                userModel.getNombreRol()
                        );

                String newRefreshToken =
                        jwtUtilService.generateRefreshToken(
                                userDetails,
                                userModel.getNombreRol()
                        );

                AuthResponseDto response =
                        new AuthResponseDto();

                response.setToken(newJwt);
                response.setRefresToken(newRefreshToken);
                response.setSuccess(true);

                return ResponseEntity.ok(response);

            }

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid refresh token");


        } catch(Exception e){

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Error refresh token::: " + e.getMessage());

        }

    }

    @GetMapping("/me")
    public ResponsesDto me(Authentication authentication){

        String correo = authentication.getName();

        UserModel me = userRepository.findByEmail(correo);

        if(me == null){
            throw new ResourceNotFoundException("Usuario no encontrado");
        }

        me.setPassword(null);

        return buildResponse(true, "Usuario encontrado", me);

    }

    @PostMapping("/pre-register")
    public ResponseEntity<?> preRegister(
            @RequestBody PreRegisterRequestDto request){

        try{

            registerService.preRegister(request);

            ResponsesDto response = new ResponsesDto();
            response.setSuccess(true);
            response.setMensaje("Correo de verificación enviado");

            return ResponseEntity.ok(response);

        } catch(Exception e){

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());

        }

    }

    @GetMapping("/verify")
    public ResponseEntity<?> verify(
            @RequestParam String token){

        String idPersona =
                authRegisterRepository
                        .findPersonaByValidToken(token);

        if(idPersona == null){
            return ResponseEntity
                    .badRequest()
                    .body("Token inválido o expirado");
        }

        ResponsesDto response = new ResponsesDto();
        response.setSuccess(true);
        response.setMensaje("Token válido");

        return ResponseEntity.ok(response);

    }

    @PostMapping("/activate-account")
    public ResponseEntity<?> activateAccount(
            @RequestBody ActivateAccountDto request){

        try{

            activateAccountService
                    .activateAccount(request);

            ResponsesDto response = new ResponsesDto();

            response.setSuccess(true);
            response.setMensaje("Cuenta activada");

            return ResponseEntity.ok(response);

        } catch(Exception e){

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());

        }

    }

    private ResponsesDto buildResponse(Boolean success, String mensaje, Object data){

        ResponsesDto res = new ResponsesDto();

        res.setSuccess(success);
        res.setMensaje(mensaje);
        res.setData(data);

        return res;

    }

}
