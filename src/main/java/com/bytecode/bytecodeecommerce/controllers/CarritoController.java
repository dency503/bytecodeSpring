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
    public ResponseEntity<CarritoCompras> agregarProductoAlCarrito(
            @PathVariable Long id,
            @PathVariable int cantidad,
            Authentication authentication
    ) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (userDetails instanceof Usuario user) {
            Optional<Cliente> clienteOptional = clienteRepository.findByUsuario(user);

            if (clienteOptional.isPresent()) {
                Cliente cliente = clienteOptional.get();
                Optional<CarritoCompras> cartOptional = carritoRepository.findByCliente(cliente);

                if (cartOptional.isPresent()) {
                    // El carrito ya existe, agregar el producto
                    CarritoCompras carrito = cartOptional.get();
                    Producto producto = productoRepository.findById(id)
                            .orElseThrow(() -> null);

                    ItemCarrito itemCarrito = new ItemCarrito();
                    itemCarrito.setProducto(producto);
                    itemCarrito.setCantidad(cantidad);
                    itemCarrito.setCarritoCompras(carrito);
                    carrito.getItems().add(itemCarrito);

                    // Guardar todos los elementos del carrito
                    carritoRepository.save(carrito);

                    return ResponseEntity.ok(carrito);
                } else {
                    // El carrito no existe, crear uno nuevo
                    CarritoCompras nuevoCarrito = new CarritoCompras();
                    nuevoCarrito.setCliente(cliente);

                    Producto producto = productoRepository.findById(id)
                            .orElseThrow(() -> null);

                    ItemCarrito itemCarrito = new ItemCarrito();
                    itemCarrito.setProducto(producto);
                    itemCarrito.setCantidad(cantidad);
                    itemCarrito.setCarritoCompras(nuevoCarrito);

                    nuevoCarrito.getItems().add(itemCarrito);

                    // Guardar todos los elementos del carrito
                    carritoRepository.save(nuevoCarrito);

                    return ResponseEntity.ok(nuevoCarrito);
                }
            } else {
                // El cliente no existe
                return ResponseEntity.notFound().build();
            }
        } else {
            // Usuario no autenticado
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/{id}/{cantidad}")
    @Transactional
    public ResponseEntity<CarritoCompras> modificarCarrito(
            @PathVariable Long id,
            @PathVariable int cantidad,
            Authentication authentication
    ) {
        System.out.println("hola");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (userDetails instanceof Usuario user) {
            Optional<Cliente> clienteOptional = clienteRepository.findByUsuario(user);

            if (clienteOptional.isPresent()) {
                Cliente cliente = clienteOptional.get();
                Optional<CarritoCompras> cartOptional = carritoRepository.findByCliente(cliente);

                if (cartOptional.isPresent()) {
                    // El carrito ya existe, buscar el producto
                    CarritoCompras carrito = cartOptional.get();
                    Optional<ItemCarrito> itemCarritoOptional = carrito.getItems().stream()
                            .filter(item -> item.getProducto().getProductoId() == (id))
                            .findFirst();

                    if (itemCarritoOptional.isPresent()) {
                        // El producto está en el carrito, actualizar la cantidad
                        ItemCarrito itemCarrito = itemCarritoOptional.get();
                        itemCarrito.setCantidad(cantidad);

                        // Guardar todos los elementos del carrito
                        carritoRepository.save(carrito);

                        return ResponseEntity.ok(carrito);
                    } else {
                        // El producto no está en el carrito
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                    }
                } else {
                    // El carrito no existe
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }
            } else {
                // El cliente no existe
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping("/item/{id}")

    public ResponseEntity<ItemCarrito> getProductoEnCarrito(@PathVariable Long id, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (userDetails instanceof Usuario user) {
            Optional<Cliente> clienteOptional = clienteRepository.findByUsuario(user);

            if (clienteOptional.isPresent()) {
                Optional<CarritoCompras> cartOptional = carritoRepository.findByCliente(clienteOptional.get());

                return cartOptional.map(carrito -> {
                    Optional<ItemCarrito> itemCarritoOptional = carrito.getItems().stream()
                            .filter(item -> item.getProducto().getProductoId() == id)
                            .findFirst();

                    return itemCarritoOptional.map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
                }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }


}
