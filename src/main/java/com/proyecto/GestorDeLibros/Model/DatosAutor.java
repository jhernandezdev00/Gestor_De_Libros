package com.proyecto.GestorDeLibros.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("name") String nombreAutor,
        @JsonAlias("birth_year") int yearNacimiento,
        @JsonAlias("death_year") int yearFallecimiento
) {
}
