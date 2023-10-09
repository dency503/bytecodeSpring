package com.bytecode.bytecodeecommerce.controllers;

import com.bytecode.bytecodeecommerce.Service.CarritoService;
import com.bytecode.bytecodeecommerce.models.ItemCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    private final CarritoService carritoService;

    @Autowired
    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @PostMapping("/agregar/{productoId}/{cantidad}")
    public void agregarProductoAlCarrito(@PathVariable Long productoId, @PathVariable int cantidad) {
        carritoService.agregarProductoAlCarrito(productoId, cantidad);
    }

    @DeleteMapping("/eliminar/{productoId}")
    public void eliminarProductoDelCarrito(@PathVariable Long productoId) {
        carritoService.eliminarProductoDelCarrito(productoId);
    }

    @GetMapping("/ver")
    public List<ItemCarrito> verCarrito() {
        return carritoService.obtenerCarrito();
    }
}
