/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dao;

import java.util.Collection;
import javax.persistence.NoResultException;
import main.entidades.Autor;
import main.entidades.Editorial;
import main.entidades.Libro;

/**
 *
 * @author Rocio
 */
public class LibroDAO extends DAO<Libro> {

    @Override
    public void guardar(Libro libro) {
        super.guardar(libro);
    }

    @Override
    public void modificar(Libro libro) {
        super.modificar(libro);
    }

    public void eliminar(Integer id) {
        Libro libro = buscarLibroPorIsbn(id);
        super.eliminar(libro);
    }

    public Libro buscarLibroPorIsbn(Integer isbn) {
        try {
            conectar();
            Libro libro = (Libro) em.createQuery("SELECT a FROM Libro a WHERE a.isbn = :isbn").setParameter("isbn", isbn).getSingleResult();
            desconectar();
            return libro;
        } catch (NoResultException e) {
            return null;
        }
    }

    public Collection<Libro> listar() {
        conectar();
        Collection<Libro> lista = em.createQuery("SELECT a FROM Libro a").getResultList();
        desconectar();
        return lista;
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        try {
            conectar();
            Libro libro = (Libro) em.createQuery("SELECT a FROM Libro a WHERE a.titulo = :titulo").setParameter("titulo", titulo).getSingleResult();
            desconectar();
            return libro;
        } catch (NoResultException e) {
            return null;
        }
    }

    public Collection<Libro> buscarLibrosPorAutor(Autor a) {
        conectar();
        Collection<Libro> lista = em.createQuery("SELECT a FROM Libro a WHERE a.autor = :autor").setParameter("autor", a).getResultList();
        desconectar();
        return lista;
    }

    public Collection<Libro> buscarLibrosPorEditorial(Editorial e) {
        conectar();
        Collection<Libro> lista = em.createQuery("SELECT a FROM Libro a WHERE a.editorial = :editorial").setParameter("editorial", e).getResultList();
        desconectar();
        return lista;
    }
}
