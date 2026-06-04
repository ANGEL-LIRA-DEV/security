package com.angel.security.service;

import com.angel.security.mapper.TelefonoMapper;
import com.angel.security.model.UsuarioListado;
import com.angel.security.repository.ITelefonoRepository;
import com.angel.security.repository.impl.UsuarioAdminRepository;
import com.angel.security.util.MailTemplateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioAdminService  implements IUsuarioAdminService {

    private final UsuarioAdminRepository repository;
    private final MailService emailService;


    @Override
    public void banearusuario(String userId) {

        repository.actualizarEstado(userId, 99);

        String correo =
                repository.obtenerCorreoUsuario(userId);

        String nombre =
                repository.obtenerNombreUsuario(userId);

        String html =
                MailTemplateUtil
                        .cuentaInactivadaTemplate(nombre);

        emailService.enviarHtml(
                correo,
                "Cuenta desactivada",
                html
        );

    }

    @Override
    public void activarUsuario(String userId) {

        repository.actualizarEstado(userId, 0);

        String correo =
                repository.obtenerCorreoUsuario(userId);

        String nombre =
                repository.obtenerNombreUsuario(userId);

        String html =
                MailTemplateUtil
                        .cuentaActivadaTemplate(nombre);

        emailService.enviarHtml(
                correo,
                "Cuenta reactivada",
                html
        );

    }

    @Override
    public List<UsuarioListado> obtenerUsuarios() {
        return repository.obtenerUsuarios();
    }
}
