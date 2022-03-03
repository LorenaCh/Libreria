/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dao;

import java.util.Collection;
import javax.persistence.NoResultException;
import main.entidades.Editorial;

/**
 *
 * @author Rocio
 */
public class EditorialDAO extends DAO<Editorial> {

    @Override
    public void guardar(Editorial editorial) {
        super.guardar(editorial);
    }

    @Override
    public void modificar(Editorial editorial) {
        super.modificar(editorial);
    }

    public void eliminar(Integer id) {
        Editorial editorial = buscarEditorialPorId(id);
        super.eliminar(editorial);
    }

    public Editorial buscarEditorialPorId(Integer id) {
        try {
            conectar();
            Editorial editorial = (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.id = :id").setParameter("id", id).getSingleResult();
            desconectar();
            return editorial;
        } catch (NoResultException e) {
            return null;
        }
    }

    public Collection<Editorial> listar() {
        conectar();
        Collection<Editorial> lista = em.createQuery("SELECT e FROM Editorial e").getResultList();
        desconectar();
        return lista;
    }

    public Editorial buscarEditorialPorNombre(String nombre) {
        try {
            conectar();
            Editorial ed = (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.nombre = :nombre").setParameter("nombre", nombre).getSingleResult();
            desconectar();
            return ed;
        } catch (NoResultException e) {
            return null;
        }
    }
}
