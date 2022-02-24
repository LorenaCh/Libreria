/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dao;

import java.util.Collection;
import main.entidades.Autor;
import main.entidades.Libro;

/**
 *
 * @author Rocio
 */
public class LibroDAO extends DAO<Libro> {
    @Override
    public void guardar(Libro libro){
        super.guardar(libro);
    }
    
    @Override
    public void modificar(Libro libro){
        super.modificar(libro   );
    }
    
    public void eliminar(Integer id){
        Libro libro = buscarLibroPorId(id);
        super.eliminar(libro);
    }
    
    public Libro buscarLibroPorId(Integer isbn){
        conectar();
        Libro libro = (Libro) em.createQuery("SELECT a FROM Libro a WHERE a.isbn = :isbn").setParameter("isbn",isbn).getSingleResult();
        desconectar();
        return libro;
    }
    
    public Collection<Libro> listar(){
        conectar();
        Collection<Libro> lista = em.createQuery("SELECT a FROM Libro a").getResultList();
        desconectar();
        return lista;
    }
}
