package es.adri.gestorResi.entidades.enums.trabajoSocial;

public enum EstadoTramite {
    PENDIENTE_INICIAR,
    EN_TRAMITE,
    PENDIENTE_ADMINISTRACION,
    SUBSANACION_DOCUMENTOS, // Cuando falta algún papel
    CONCEDIDO,
    DENEGADO
}
