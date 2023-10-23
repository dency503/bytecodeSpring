package com.bytecode.bytecodeecommerce.controllers;

import com.bytecode.bytecodeecommerce.Service.PagoService;
import com.bytecode.bytecodeecommerce.models.Pago;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/pagos")
@Validated
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;



    @GetMapping
    public ResponseEntity<Page<Pago>> obtenerTodosPagos(Pageable pageable) {
        Page<Pago> pagos = pagoService.obtenerTodosPagos(pageable);
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPagoPorId(@PathVariable int id) {
        Pago pago = pagoService.obtenerPagoPorId(id);
        return pago != null ?
                new ResponseEntity<>(pago, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> guardarPago(@Valid @RequestBody Pago pago) {
        pagoService.guardarPago(pago);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> actualizarPago(@PathVariable int id, @Valid @RequestBody Pago pago) {
        Pago existingPago = pagoService.obtenerPagoPorId(id);
        if (existingPago != null) {
            existingPago.setMetodoPago(pago.getMetodoPago());
            existingPago.setCantidad(pago.getCantidad());
            // También puedes manejar otras propiedades y relaciones aquí según tus necesidades
            pagoService.guardarPago(existingPago);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> eliminarPago(@PathVariable int id) {
        if (pagoService.obtenerPagoPorId(id) != null) {
            pagoService.eliminarPago(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}