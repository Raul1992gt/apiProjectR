package org.example.apiprojectr.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "Clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 50)
    private String apellido;

    @Column(length = 70)
    private String email;

    @Column(length = 9)
    private String dni;

    @Column(length = 50)
    private String direccion;

    @Column(length = 50)
    private String rrss;

    @Column(length = 20)
    private String usuario;

    @Column(length = 9)
    private String telefono;

    @Column(length = 100)
    private String notas;

    @Column(name = "fc_creacion", updatable = false, nullable = false)
    private LocalDateTime fcCreacion;

    @Column(name = "fc_modif")
    private LocalDateTime fcModif;

    private boolean activo;
}
