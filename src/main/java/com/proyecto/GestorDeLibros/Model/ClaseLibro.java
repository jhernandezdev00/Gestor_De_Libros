package com.proyecto.GestorDeLibros.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Libros")
public class ClaseLibro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String titulo;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ClaseAutor> DatosAutor;

    private List<String> generos;
    private List<String> idiomasDisponibles;
    private int numeroDeDescargas;
    private String derechoAutor;

    public ClaseLibro() {}

    public ClaseLibro(DatosLibros data) {
        this.titulo = data.titulo();
        this.generos = data.generos();
        this.idiomasDisponibles = data.idiomasDisponibles();
        this.numeroDeDescargas = data.numeroDeDescargas();
        this.derechoAutor = data.derechoAutor();
        setDatosAutor(data.DatosAutor());
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<ClaseAutor> getDatosAutor() {
        return DatosAutor;
    }

    public void setDatosAutor(List<DatosAutor> datosAutor) {
        this.DatosAutor = datosAutor.stream()
                .map(autor -> {
                    ClaseAutor claseAutor = new ClaseAutor(autor);
                    claseAutor.setLibro(this);
                    return claseAutor;
                })
                .collect(Collectors.toList());
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public List<String> getIdiomasDisponibles() {
        return idiomasDisponibles;
    }

    public void setIdiomasDisponibles(List<String> idiomasDisponibles) {
        this.idiomasDisponibles = idiomasDisponibles;
    }

    public String getDerechoAutor() {
        return derechoAutor;
    }

    public void setDerechoAutor(String derechoAutor) {
        this.derechoAutor = derechoAutor;
    }

    public int getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(int numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "titulo='" + titulo + '\'' +
                ", DatosAutor=" + DatosAutor +
                ", generos=" + generos +
                ", idiomasDisponibles=" + idiomasDisponibles +
                ", numeroDeDescargas=" + numeroDeDescargas +
                ", derechoAutor='" + derechoAutor + '\'';
    }
}
