package com.bytecode.bytecodeecommerce.controllers;

import com.bytecode.bytecodeecommerce.Repository.CarritoRepository;
import com.bytecode.bytecodeecommerce.Repository.ClienteRepository;
import com.bytecode.bytecodeecommerce.Repository.ItemCarritoRepository;
import com.bytecode.bytecodeecommerce.Repository.ProductoRepository;
import com.bytecode.bytecodeecommerce.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoRepository carritoRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final ItemCarritoRepository itemCarritoRepository;
    @GetMapping
    public Optional<CarritoCompras> getCarrito(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (userDetails instanceof Usuario user) {

            Optional<Cliente> clienteOptional = clienteRepository.findByUsuario(user);

           return carritoRepository.findByCliente(clienteOptional.orElse(null));
            }
        return Optional.empty();
    }

    @DeleteMapping("/item/{id}")
    @Transactional
    public ResponseEntity<String> eliminarItem(@PathVariable Long id, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (userDetails instanceof Usuario user) {
            Optional<Cliente> clienteOptional = clienteRepository.findByUsuario(user);

            if (clienteOptional.isPresent()) {
                Optional<CarritoCompras> cart = carritoRepository.findByCliente(clienteOptional.get());

                if (cart.isPresent()) {
                    CarritoCompras carrito = cart.get();
                    Optional<ItemCarrito> itemCarritoOptional = itemCarritoRepository.findById(id);

                    if (itemCarritoOptional.isPresent()) {
                        ItemCarrito item = itemCarritoOptional.get();

                        // Eliminar el elemento del carrito
                        carrito.getItems().remove(item);

                        // Guardar los cambios en el carrito de compras
                        carritoRepository.save(carrito);

                        // Eliminar el elemento del repositorio de items del carrito
                        itemCarritoRepository.delete(item);

                        return ResponseEntity.ok("Elemento eliminado del carrito.");
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Elemento no encontrado.");
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrito de compras no encontrado.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado.");
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso no autorizado.");
    }


    @PostMapping("/{id}/{cantidad}")
    @Transactional
    public CarritoCompras agregarProductoAlCarrito(@PathVariable Long id, @PathVariable int cantidad,Authentication authentication) {
        Optional<Producto> productoOptional = productoRepository.findById(id);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (userDetails instanceof Usuario user) {

            Optional<Cliente> clienteOptional = clienteRepository.findByUsuario(user);

           Optional<CarritoCompras> cart =  carritoRepository.findByCliente(clienteOptional.orElse(null));

        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();


            if (cart.isPresent()) {
                CarritoCompras carrito =cart.get();
                ItemCarrito itemCarrito = new ItemCarrito();
                itemCarrito.setProducto(producto);
                itemCarrito.setCantidad(cantidad);
                itemCarrito.setCarritoCompras(cart.get());
                carrito.getItems().add(itemCarrito);
                System.out.println("se esta guardando en la cart present");
                for (ItemCarrito item : cart.get().getItems()) {
                    itemCarritoRepository.save(item);
                }
                return carritoRepository.save(carrito);
            } else {
                // Manejar el caso cuando el carrito no existe
                // Aquí puedes crear un nuevo carrito con el producto
                CarritoCompras nuevoCarrito = new CarritoCompras();
                nuevoCarrito.setCliente(clienteOptional.orElse(null)); // Asigna el cliente si es necesario
                ItemCarrito itemCarrito = new ItemCarrito();
                itemCarrito.setProducto(producto);
                itemCarrito.setCantidad(cantidad);
                itemCarrito.setCarritoCompras(nuevoCarrito);
                nuevoCarrito.getItems().add(itemCarrito);
                for (ItemCarrito item : nuevoCarrito.getItems()) {
                    itemCarritoRepository.save(item);
                }
                return carritoRepository.save(nuevoCarrito);
            }
        } else {
            // Manejar el caso cuando el producto no existe
            return null;
        }
    } return null;
}}
