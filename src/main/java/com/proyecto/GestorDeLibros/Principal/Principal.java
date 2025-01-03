package com.proyecto.GestorDeLibros.Principal;

import com.proyecto.GestorDeLibros.Model.*;
import com.proyecto.GestorDeLibros.Services.ConverDatos;
import com.proyecto.GestorDeLibros.Services.GestionAPI;
import com.proyecto.GestorDeLibros.repository.ClaseLibroRepository;

import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private final GestionAPI API = new GestionAPI();
    private final ConverDatos convertdatos = new ConverDatos();
    private int opcion;
    private Scanner scanner = new Scanner(System.in);
    private Scanner scannerint = new Scanner(System.in);
    private int sizeList;
    private ClaseLibroRepository repositorio;

    public Principal(ClaseLibroRepository repository) {
        this.repositorio = repository;
    }

    public void getMenu() {
        do{
            System.out.println("\n======== MENÚ PRINCIPAL ========");
            System.out.println("\nDIGITE UN NUMERO PARA ESCOGER UNA OPCION");
            System.out.println("\n1. BUSCAR LIBRO POR TITULO");
            System.out.println("2. LISTAR LIBROS REGISTRADOS");
            System.out.println("3. LISTAR AUTORES REGISTRADOS");
            System.out.println("4. LISTAR AUTORES VIVOS POR AÑO");
            System.out.println("5. LISTAR LIBROS POR IDIOMA");
            System.out.println("0. SALIR");
            System.out.print("Selecciona una opción: ");
            opcion = scannerint.nextInt();

            switch (opcion) {
                case 0:
                    break;
                case 1:
                    System.out.println("Ingrese el nombre del libro a buscar:");
                    String libroBuscar = scanner.nextLine();
                    libroBuscar = libroBuscar.toUpperCase();
                    searchBook(libroBuscar);
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        }while (opcion != 0);
        scanner.close();
        scannerint.close();
    }

    public void searchBook(String libroBuscar) {
        var json = API.getDatos(URL_BASE + "?search=" + libroBuscar.replace(" ", "+"));
        mainDatos datosBusqueda = convertdatos.getDatos(json, mainDatos.class);

        int seleccionLibro = 0;


        if(!(datosBusqueda.listaResultados().isEmpty())){
            System.out.println("====== LIBRO ENCONTRADO ====");
            sizeList = datosBusqueda.listaResultados().size();

            if(sizeList==1){
                seleccionLibro = 0;
            }else{
                for (int i=0; i<sizeList; i++){
                    System.out.println(i+". "+datosBusqueda.listaResultados().get(i).titulo());
                }
                do{
                    System.out.println("Digite el libro de su seleccion:");
                    seleccionLibro = scannerint.nextInt();

                    if (seleccionLibro < 0 || seleccionLibro >= sizeList) {
                        System.out.println("Número fuera de rango. Intenta de nuevo.");
                    }
                }while (seleccionLibro < 0 || seleccionLibro >= sizeList);

            }

            ClaseLibro libro = new ClaseLibro(datosBusqueda.listaResultados().get(seleccionLibro));
            System.out.println(libro);
            repositorio.save(libro);

        }else{
            System.out.println("LIBRO NO ENCONTRADO");
        }

    }

}
