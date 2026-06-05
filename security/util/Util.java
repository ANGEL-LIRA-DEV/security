package com.angel.security.util;

public class Util {

    public static Boolean OKSUCCESS = Boolean.TRUE;
    public static Boolean ERRORSUCCESS = Boolean.FALSE;

    public static String OKQUERY = "Consulta correcta";
    public static String OKFOUND = "Registro encontrado";
    public static String NOTFOUND = "Registro no encontrado";
    public static String NOTUSER = "Usuario y/o contraseña incorrectos";
    public static String LOGINFAIL = "Intento de login erróneo";
    public static String LOGINOK = "Login correcto de usuario";
    public static String TOKENINVALID = "Token inválido";
    public static String TOKENVALID = "Token válido";
    public static String SESSIONVALID = "Sesión válida";
    public static String REGDISABLE = "Registro desactivado";
    public static String ERRVALIDATION = "Error de validación";
    public static String ERRINTERNAL = "Error interno";
    public static String ERRBUSINESS = "Error de negocio";
    public static String ERRDTO = "La DATA del DTO no puede ser null";

    public static String AUTHENDPOINT = "/api/v1/auth/login";
    public static String REFRESHENDPOINT = "/api/v1/auth/refresh";
    public static String MESSAGECREATE = "/api/v1/messages/create";
    public static String PREREGISTERENDPOINT = "/api/v1/auth/pre-register";
    public static String VERIFYENDPOINT = "/api/v1/auth/verify";
    public static String ACTIVATEACCOUNTENDPOINT = "/api/v1/auth/activate-account";
    public static String CATESTADOENDPOINT = "/api/v1/catalogos/estados";
    public static String CATCIUDADENDPOINT = "/api/v1/catalogos/ciudades/**";
    public static String CATPAISESENDPOINT = "/api/v1/catalogos/paises";

    public static Integer DEACTIVATECODE = 99;
    public static Integer ACTIVATECODE = 0;

}
