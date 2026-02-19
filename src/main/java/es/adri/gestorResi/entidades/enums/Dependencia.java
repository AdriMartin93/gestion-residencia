package es.adri.gestorResi.entidades.enums;

public enum Dependencia {

    AUTONOMO("Sin dependencia reconocida"),
    GRADO_I("Grado I - Dependencia moderada"),
    GRADO_II("Grado II - Dependencia severa"),
    GRADO_III("Grado III - Gran dependencia");

    private final String descripcion;

    Dependencia(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDescripcion() {
        return descripcion;
    }
}
