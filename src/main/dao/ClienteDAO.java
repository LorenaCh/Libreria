package main.dao;

import javax.persistence.NoResultException;
import main.entidades.Cliente;

public class ClienteDAO extends DAO<Cliente> {

    public void guardar(Cliente cliente) {
        super.guardar(cliente);
    }

    public void modificar(Cliente cliente) {
        super.modificar(cliente);
    }

    public void eliminar(Integer id) {
        Cliente cliente=buscarClientePorId(id);
        super.eliminar(cliente);
    }

    public Cliente buscarClientePorId(Integer id) {
        try {
            conectar();
            Cliente cliente = (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.id = :id").setParameter("id", id).getSingleResult();
            desconectar();
            return cliente;
        } catch (NoResultException e) {
            return null;
        }
    }
    
    

}
