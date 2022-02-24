package main.dao;

import java.util.Collection;
import main.entidades.Autor;

public class AutorDAO extends DAO<Autor>{
    
    @Override
    public void guardar(Autor autor){
        super.guardar(autor);
    }
    
    @Override
    public void modificar(Autor autor){
        super.modificar(autor);
    }
    
    public void eliminar(Integer id){
        Autor autor = buscarAutorPorId(id);
        super.eliminar(autor);
    }
    
    public Autor buscarAutorPorId(Integer id){
        conectar();
        Autor autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.id = :id").setParameter("id", id).getSingleResult();
        desconectar();
        return autor;
    }
    
    public Collection<Autor> listar(){
        conectar();
        Collection<Autor> lista = em.createQuery("SELECT a FROM Autor a").getResultList();
        desconectar();
        return lista;
    }
}
