package es.adri.gestorResi.entidades.enums.Caidas;

public enum ConsecuenciaCaida {
    SIN_LESION("Sin lesiones aparentes"),
    EROSION("Erosión / Raspadura"),
    HEMATOMA("Hematoma / Contusión leve"),
    HERIDA_CON_SUTURA("Herida que requiere sutura"),
    ESGUINCE_LUXACION("Esguince o luxación"),
    FRACTURA_CADERA("Fractura de cadera"),
    FRACTURA_OTRA("Otras fracturas (muñeca, húmero, etc.)"),
    TRAUMA_CRANEAL("Traumatismo craneoencefálico"),
    DOLOR_PERSISTENTE("Dolor persistente sin lesión visible"),
    FALLECIMIENTO("Fallecimiento"),
    OTRA("Otra consecuencia no especificada");

    private final String descripcion;

    ConsecuenciaCaida(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}