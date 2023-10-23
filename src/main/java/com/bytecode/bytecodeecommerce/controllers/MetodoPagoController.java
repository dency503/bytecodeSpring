package com.bytecode.bytecodeecommerce.controllers;



import com.bytecode.bytecodeecommerce.Service.MetodoPagoService;
import com.bytecode.bytecodeecommerce.models.MetodoPago;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/metodos-pago")
@Validated
@RequiredArgsConstructor
public class MetodoPagoController {

    private final MetodoPagoService metodoPagoService;



    @GetMapping
    public ResponseEntity<Page<MetodoPago>> obtenerTodosMetodosPago(Pageable pageable) {
        Page<MetodoPago> metodosPago = metodoPagoService.obtenerTodosMetodosPago(pageable);
        return new ResponseEntity<>(metodosPago, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPago> obtenerMetodoPagoPorId(@PathVariable int id) {
        MetodoPago metodoPago = metodoPagoService.obtenerMetodoPagoPorId(id);
        return metodoPago != null ?
                new ResponseEntity<>(metodoPago, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> guardarMetodoPago(@Valid @RequestBody MetodoPago metodoPago) {
        metodoPagoService.guardarMetodoPago(metodoPago);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> actualizarMetodoPago(@PathVariable int id, @Valid @RequestBody MetodoPago metodoPago) {
        MetodoPago existingMetodoPago = metodoPagoService.obtenerMetodoPagoPorId(id);
        if (existingMetodoPago != null) {
            existingMetodoPago.setTipoMetodo(metodoPago.getTipoMetodo());
            // También puedes manejar otras propiedades y relaciones aquí según tus necesidades
            metodoPagoService.guardarMetodoPago(existingMetodoPago);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> eliminarMetodoPago(@PathVariable int id) {
        if (metodoPagoService.obtenerMetodoPagoPorId(id) != null) {
            metodoPagoService.eliminarMetodoPago(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
