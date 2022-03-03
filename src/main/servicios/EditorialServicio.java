package main.servicios;

import java.util.Collection;
import java.util.Scanner;
import main.dao.EditorialDAO;
import main.entidades.Editorial;

public class EditorialServicio {
    private EditorialDAO dao = null;

    public EditorialServicio() {
        dao = new EditorialDAO();
    }
    
    public Editorial crearEditorial(){
        Editorial a = null;
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        try {
            String nombre;
            System.out.println("Ingrese nombre de la editorial");
            nombre = sc.next();
            while(nombre.trim().isEmpty()){
                System.out.println("El nombre no puede ser vacio. Ingrese nuevamente");
                nombre = sc.next();
            }
            a = dao.buscarEditorialPorNombre(nombre);
            if(a != null){
                System.out.println("Editorial existente"+a);
                return null;
            }
            a = new Editorial();
            a.setNombre(nombre);
            a.setAlta(Boolean.TRUE);
            dao.guardar(a);
            return a;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }   
    
    public Editorial darBaja(){
        Editorial a = null;
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        try {
            System.out.println("Ingrese id de la editorial");
            a = dao.buscarEditorialPorId(sc.nextInt());
            if(a == null){
                System.out.println("Editorial inexistente");
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
    
    public Editorial buscarEditorialPorId(){
        Editorial a = new Editorial();
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        try {
            System.out.println("Ingrese id del autor");
            a = dao.buscarEditorialPorId(sc.nextInt());
            return a;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Editorial modificar(){
        Editorial a = new Editorial();
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        try {
            System.out.println("Ingrese id del autor ha modificar");
            a = dao.buscarEditorialPorId(sc.nextInt());
            if(a == null){
                System.out.println("Editorial inexistente");
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
    public void mostrarEditoriales(){
        try {
            Collection<Editorial> lista = dao.listar();
            if(lista.isEmpty()){
                System.out.println("Lista vacia");
                return;
            }
            
            for (Editorial editorial : lista) {
                System.out.println(editorial);
            }
            
        } catch (Exception e) {
            throw e;
        }
    }
}
