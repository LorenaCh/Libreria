
package main.servicios;

import java.util.Collection;
import java.util.Scanner;
import main.dao.AutorDAO;
import main.entidades.Autor;

/**
 *
 * @author Rocio
 */
public class AutorServicio {
    private final AutorDAO dao;

    public AutorServicio() {
        this.dao = new AutorDAO();
    }
    
    public Autor crearAutor(){
        Autor a = null;
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        try {
            String nombre;
            System.out.println("Ingrese nombre del autor");
            nombre  = sc.next();
            while(nombre.trim().isEmpty()){
                System.out.println("El nombre no puede ser vacio. Ingrese nuevamente");
                nombre = sc.next();
            }
            a = dao.buscarAutorPorNombre(nombre);
            if(a != null){
                System.out.println("Autor existente");
                return a;
            }
            a = new Autor();
            a.setNombre(nombre);
            a.setAlta(Boolean.TRUE);
            dao.guardar(a);
            return a;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }   
    
    public Autor darBaja(){
        Autor a = null;
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        try {
            System.out.println("Ingrese id del autor");
            a = dao.buscarAutorPorId(sc.nextInt());
            if(a == null){
                System.out.println("Autor inexistente");
                return null;
            } 
            a.setAlta(Boolean.FALSE);
            dao.modificar(a);
            return a;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Autor buscarAutorPorId(){
        Autor a = null;
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        try {
            System.out.println("Ingrese id del autor");
            a = dao.buscarAutorPorId(sc.nextInt());
            return a;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Autor modificar(){
        Autor a = new Autor();
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        try {
            System.out.println("Ingrese id del autor ha modificar");
            a = dao.buscarAutorPorId(sc.nextInt());
            if(a == null){
                System.out.println("Autor inexistente");
                return null;
            }
            System.out.println("Ingrese nuevo nombre");
            a.setNombre(sc.next());
            dao.modificar(a);
            return a;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Autor buscarAutorPorNombre(){
        Autor a= null;
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        try {
            System.out.println("Ingrese el nombre del autor a buscar");
            a = dao.buscarAutorPorNombre(sc.next());
            return a;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public void mostrarAutores(){
        try {
            Collection<Autor> lista = dao.listar();
            if(lista.isEmpty()){
                System.out.println("Lista vacia");
                return;
            }
            
            for (Autor autor : lista) {
                System.out.println(autor);
            }
            
        } catch (Exception e) {
            throw e;
        }
    }
    
}
