package com.bytecode.bytecodeecommerce.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "inicioSesion", schema = "Cliente")
public class ClienteInicioSesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inicioSesionID")
    private int inicioSesionId;

    @ManyToOne
    @JoinColumn(name = "ClienteID")
    private Cliente cliente;

    @Column(name = "FechaInicio")
    private Date fechaInicio;

    @Column(name = "FechaUltimoAcceso")
    private Date fechaUltimoAcceso;
}
