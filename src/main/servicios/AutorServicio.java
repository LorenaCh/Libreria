/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.servicios;

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
        Autor a = new Autor();
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        try {
            System.out.println("Ingrese nombre del autor");
            a.setNombre(sc.next());
            a.setAlta(Boolean.TRUE);
            dao.guardar(a);
            return a;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Autor darBaja(){
        Autor a = new Autor();
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        try {
            System.out.println("Ingrese id del autor");
            a = dao.buscarAutorPorId(sc.nextInt());
            a.setAlta(Boolean.FALSE);
            dao.modificar(a);
            return a;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Autor buscarAutorPorId(){
        Autor a = new Autor();
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
            System.out.println("Ingrese nuevo nombre");
            a.setNombre(sc.next());
            dao.modificar(a);
            return a;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}
