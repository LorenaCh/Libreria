package main.servicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import main.dao.AutorDAO;
import main.dao.EditorialDAO;
import main.dao.LibroDAO;
import main.entidades.Autor;
import main.entidades.Editorial;
import main.entidades.Libro;

public class LibroServicio {
    private LibroDAO dao = null;
    private AutorDAO daoAutor = null;
    private EditorialDAO daoEditorial = null;

    public LibroServicio() {
        dao  = new LibroDAO();
        daoAutor = new AutorDAO();
        daoEditorial = new EditorialDAO();
    }
    
    public Libro crearLibro(){
        Libro libro = null;
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        try {
            System.out.println("Ingrese titulo del libro");
            String titulo = sc.next();
            while(titulo.trim().isEmpty()){
                System.out.println("El nombre no puede ser vacio. Ingrese nuevamente");
                titulo = sc.next();
            }
            libro = dao.buscarLibroPorTitulo(titulo);
            if(libro != null){
                System.out.println("Libro existente "+libro);
                return null;
            }
            libro = new Libro();
            libro.setTitulo(titulo);
            System.out.println("Ingrese año");
            int anio=sc.nextInt();
            libro.setAnio(anio);
            System.out.println("Ingrese cantidad de ejemplares");
            libro.setEjemplares(sc.nextInt());
            libro.setEjemplaresPrestados(0);
            libro.setEjemplaresRestantes(libro.getEjemplares());
            libro.setAlta(Boolean.TRUE);
            System.out.println("Seleccione algun autor o ingrese uno nuevo");
            Collection<Autor> autores = daoAutor.listar();
            int ind = 1;
            for (Autor autor : autores) {
                System.out.println(ind + ". " +autor);
                ind++;
            }
            System.out.println(ind + ". Ingresar nuevo autor");
            int opcion = sc.nextInt();
            while (opcion <1 || opcion > ind){
                System.out.println("Opcion invalida. Ingrese nuevamente");
                opcion = sc.nextInt();
            }
            if(opcion == ind){
                Autor a = new AutorServicio().crearAutor();
                System.out.println("Se creo "+a);
                libro.setAutor(a);
            }else
                libro.setAutor((Autor) autores.toArray()[ind-1]);
            System.out.println("Seleccion alguna editorial o ingrese uno nuevo");
            Collection<Editorial> editoriales = daoEditorial.listar();
            
            ind = 1;
            for (Editorial editorial : editoriales) {
                System.out.println(ind + ". "+editorial);
                ind++;
            }
            System.out.println(ind + ". Ingresar nueva editorial");
            opcion = sc.nextInt();
            while (opcion <1 || opcion > ind){
                System.out.println("Opcion invalida. Ingrese nuevamente");
                opcion = sc.nextInt();
            }
            if(opcion == ind){
                Editorial e = new EditorialServicio().crearEditorial();
                libro.setEditorial(e);
            }
            else
                libro.setEditorial((Editorial)editoriales.toArray()[ind-1]);
            
            dao.guardar(libro);
            return libro;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public void mostrarLibros(){
        try {
            Collection<Libro> lista = dao.listar();
            if(lista.isEmpty()){
                System.out.println("Lista vacia");
                return;
            }
            
            for (Libro libro : lista) {
                System.out.println(libro);
            }
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Libro prestarLibro(){
        Libro libro = null;
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        try {
            System.out.println("Ingrese titulo del libro");
            libro = dao.buscarLibroPorTitulo(sc.next());
            if(libro == null){
                System.out.println("Libro inexistente");
                return null;
            }
            if(libro.getEjemplaresRestantes()==0){
                System.out.println("No quedan ejemplares para prestar\n"+libro);
                return libro;
            }
            libro.setEjemplaresPrestados(libro.getEjemplaresPrestados()+1);
            libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()-1);
            dao.modificar(libro);
            System.out.println("Se ha prestado "+libro);
            return libro;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Libro recibirLibro(){
        Libro libro = null;
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        try {
            System.out.println("Ingrese titulo del libro");
            libro = dao.buscarLibroPorTitulo(sc.next());
            if(libro == null){
                System.out.println("Libro inexistente");
                return null;
            }
            if(libro.getEjemplaresRestantes() == libro.getEjemplares()){
                System.out.println("Ejemplar de mas\n"+libro);
                return libro;
            }
            libro.setEjemplaresPrestados(libro.getEjemplaresPrestados()-1);
            libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()+1);
            dao.modificar(libro);
            System.out.println("Se ha devuelto "+libro);
            return libro;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void mostrarLibrosDelAutor() throws Exception{
        Autor a = null;
        Scanner sc  = new Scanner(System.in).useDelimiter("\n");
        try {
            System.out.println("Ingrese nombre del autor:");
            a = daoAutor.buscarAutorPorNombre(sc.next());
            if(a == null){
                System.out.println("Autor inexistente");
                return;
            }
            Collection<Libro> lista = dao.buscarLibrosPorAutor(a);
            if(lista.isEmpty())
                System.out.println("Lista vacia");
            else{
                for (Libro libro : lista) {
                    System.out.println(libro);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void mostrarLibrosDeLaEditorial() throws Exception{
        Editorial a = null;
        Scanner sc  = new Scanner(System.in).useDelimiter("\n");
        try {
            System.out.println("Ingrese nombre del autor:");
            a = daoEditorial.buscarEditorialPorNombre(sc.next());
            if(a == null){
                System.out.println("Editorial inexistente");
                return;
            }
            Collection<Libro> lista = dao.buscarLibrosPorEditorial(a);
            if(lista.isEmpty())
                System.out.println("Lista vacia");
            else{
                for (Libro libro : lista) {
                    System.out.println(libro);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
