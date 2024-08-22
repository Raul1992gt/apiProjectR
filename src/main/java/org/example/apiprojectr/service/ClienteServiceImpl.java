package org.example.apiprojectr.service;

import org.example.apiprojectr.model.Cliente;
import org.example.apiprojectr.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clrp;

    @Override
    public List<Cliente> findByNombre(String nombre) {
        return clrp.findByNombre(nombre);
    }

    @Override
    public List<Cliente> findByEmail(String email) {
        return clrp.findByEmail(email);
    }

    @Override
    public List<Cliente> findByDni(String dni) {
        return clrp.findByDni(dni);
    }

    @Override
    public List<Cliente> findByRrss(String rrss) {
        return clrp.findByRrss(rrss);
    }

    @Override
    public List<Cliente> findByNombreContainingIgnoreCase(String nombre) {
        return  clrp.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<Cliente> findByActivo(boolean activo) {
        return clrp.findByActivo(activo);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clrp.findById(id);
    }

    @Override
    public List<Cliente> findAll() {
        return clrp.findAll();
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clrp.save(cliente);
    }

    @Override
    public void deleteById(Long id) {
        clrp.deleteById(id);
    }
}
