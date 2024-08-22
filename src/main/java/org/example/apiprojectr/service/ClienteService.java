package org.example.apiprojectr.service;

import org.example.apiprojectr.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    List<Cliente> findByNombre(String nombre);

    List<Cliente> findByEmail(String email);

    List<Cliente> findByDni(String dni);

    List<Cliente> findByRrss(String rrss);

    List<Cliente> findByNombreContainingIgnoreCase(String nombre);

    List<Cliente> findByActivo(boolean activo);

    Optional<Cliente> findById(Long id);

    List<Cliente> findAll();

    Cliente save(Cliente cliente);

    void deleteById(Long id);
}
