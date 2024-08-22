package org.example.apiprojectr.controller;

import org.example.apiprojectr.service.ClienteServiceImpl;
import org.example.apiprojectr.model.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteServiceImpl clrep;

    // Inicio Métodos GET

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clrep.findAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        Cliente cliente = clrep.findById(id)
                .orElseThrow(() -> {
                    logger.error("Cliente con ID {} no encontrado", id);
                    return new RuntimeException("Cliente no encontrado");
                });

        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Cliente>> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(clrep.findByNombre(nombre));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<Cliente>> buscarPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(clrep.findByEmail(email));
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<List<Cliente>> buscarPorDni(@PathVariable String dni) {
        return ResponseEntity.ok(clrep.findByDni(dni));
    }

    @GetMapping("/rrss/{rrss}")
    public ResponseEntity<List<Cliente>> buscarPorRrss(@PathVariable String rrss) {
        return ResponseEntity.ok(clrep.findByRrss(rrss));
    }

    /**
     * Método para buscar por parte del nombre.
     *
     * @param q
     * @return
     */
    @GetMapping("/containing/{q}")
    public ResponseEntity<List<Cliente>> buscarPorNombreContainingIgnoreCase(@PathVariable String q) {
        return ResponseEntity.ok(clrep.findByNombreContainingIgnoreCase(q));
    }

    @GetMapping("/activo/{activo}")
    public ResponseEntity<List<Cliente>> buscarPorActivo(@PathVariable boolean activo) {
        return ResponseEntity.ok(clrep.findByActivo(activo));
    }

    // Fin Métodos GET
    // Inicio Métodos POST

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        logger.info("Recibida solicitud para crear un nuevo cliente: {}", cliente);

        cliente.setFcCreacion(LocalDateTime.now());
        cliente.setActivo(true);

        Cliente nuevoCliente = clrep.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    // Fin Métodos POST
    // Inicio Métodos PUT

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteActualizado) {

        clienteActualizado.setIdCliente(id);
        clienteActualizado.setFcModif(LocalDateTime.now());

        Cliente clienteActualizadoGuardado = clrep.save(clienteActualizado);
        logger.info("Cliente actualizado con ID: {}", id);

        return ResponseEntity.ok(clienteActualizadoGuardado);
    }

    /**
     * @param id
     * @param activar - true para activar, false para desactivar
     * @return
     */
    @PutMapping("/activar/{id}")
    public ResponseEntity<Cliente> activarCliente(@PathVariable Long id, @RequestParam boolean activar) {
        Optional<Cliente> clienteBuscado = clrep.findById(id);
        if (clienteBuscado.isPresent()) {
            Cliente cliente = clienteBuscado.get();

            cliente.setFcModif(LocalDateTime.now());
            cliente.setActivo(activar);
            return ResponseEntity.ok(clrep.save(cliente));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Fin Métodos PUT
    // Inicio Métodos DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        logger.info("Recibida solicitud para eliminar cliente con ID: {}", id);

        clrep.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Fin Métodos DELETE

}
