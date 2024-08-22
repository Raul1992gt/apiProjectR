package org.example.apiprojectr.repository;

import org.example.apiprojectr.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNombre(String nombre);

    List<Cliente> findByEmail(String email);

    List<Cliente> findByActivo(boolean activo);

    List<Cliente> findByNombreContainingIgnoreCase(String nombre);

    List<Cliente> findByRrss(String rrss);

    List<Cliente> findByDni(String dni);
}
