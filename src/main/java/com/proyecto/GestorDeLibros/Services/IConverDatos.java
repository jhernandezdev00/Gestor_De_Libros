package com.proyecto.GestorDeLibros.Services;

public interface IConverDatos {
    <T> T getDatos(String json, Class<T> clase);
}
