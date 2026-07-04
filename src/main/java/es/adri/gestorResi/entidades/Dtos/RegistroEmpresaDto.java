package es.adri.gestorResi.entidades.Dtos;


import es.adri.gestorResi.entidades.personas.Empresa;
import es.adri.gestorResi.entidades.personas.Empleado;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistroEmpresaDto {

    @NotNull(message = "Los datos de la empresa son obligatorios")
    @Valid
    private Empresa empresa;

    @NotNull(message = "Los datos del empleado administrador son obligatorios")
    @Valid
    private Empleado administrador;
}
