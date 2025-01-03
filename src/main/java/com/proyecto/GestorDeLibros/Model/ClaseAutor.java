package com.proyecto.GestorDeLibros.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "Autores")
public class ClaseAutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombreAutor;
    private int yearNacimiento;
    private int yearFallecimiento;
    @ManyToOne
    @JoinColumn(name = "libro_id")
    private ClaseLibro libro;

    public ClaseAutor(){}


    public ClaseAutor(DatosAutor autor){
        this.nombreAutor = autor.nombreAutor();
        this.yearNacimiento = autor.yearNacimiento();
        this.yearFallecimiento = autor.yearFallecimiento();
    }



    public ClaseLibro getLibro() {
        return libro;
    }

    public void setLibro(ClaseLibro libro) {

        this.libro = libro;
    }

    public int getYearFallecimiento() {
        return yearFallecimiento;
    }

    public void setYearFallecimiento(int yearFallecimiento) {
        this.yearFallecimiento = yearFallecimiento;
    }

    public int getYearNacimiento() {
        return yearNacimiento;
    }

    public void setYearNacimiento(int yearNacimiento) {
        this.yearNacimiento = yearNacimiento;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    @Override
    public String toString() {
        return "nombreAutor='" + nombreAutor + '\'' +
                ", yearNacimiento='" + yearNacimiento + '\'' +
                ", yearFallecimiento='" + yearFallecimiento + '\'';
    }
}
