package com.bytecode.bytecodeecommerce.controllers;


import com.bytecode.bytecodeecommerce.Service.PaymentService;
import com.bytecode.bytecodeecommerce.Service.VentaService;
import com.bytecode.bytecodeecommerce.dao.ConfirmacionPagoRequest;
import com.bytecode.bytecodeecommerce.dao.PaymentIntentDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    @Value("${stripe.key.secret}")
    String secretKey;
    private final PaymentService paymentService;
    private final VentaService ventaService;



    @PostMapping("/create")
    public ResponseEntity<String> payment(@RequestBody PaymentIntentDTO paymentIntentDto) throws StripeException {
        PaymentIntent paymentIntent = paymentService.paymentIntent(paymentIntentDto);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }
    @PostMapping("/procesar-pago")
    public String procesarPago(@RequestBody String token) throws StripeException {
        // La transacción se ha completado con éxito
        return paymentService.procesarPago(token);
    }
    @PostMapping("/confirm/{paymentIntentId}")
    public ResponseEntity<String> confirmPaymentIntent(@PathVariable String paymentIntentId) {

            PaymentIntent paymentIntent = paymentService.confirm(paymentIntentId);
            String paymentStr = paymentIntent.toJson();
            return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
        }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> cancel(@PathVariable String id) throws StripeException {
        PaymentIntent paymentIntent = paymentService.cancelPaymentIntent(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("/sucesss")
    public ResponseEntity<String> confirmarPago(@RequestBody ConfirmacionPagoRequest request) {
        Stripe.apiKey = secretKey;

        try {
            // Verificar el pago con la API de Stripe
            PaymentIntent paymentIntent = PaymentIntent.retrieve(request.getPaymentIntentId());
            if ("succeeded".equals(paymentIntent.getStatus())) {
                // El pago ha sido exitoso, guarda los detalles de la venta en la base de datos
                guardarDetallesVenta(request.getCarritoId());
                return ResponseEntity.ok("Pago confirmado exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al confirmar el pago");
            }
        } catch (StripeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error de servidor");
        }
    }

    private void guardarDetallesVenta(Long id) {
        System.out.println("Miami me lo confirmo ;3");
ventaService.convertirCarritoAVenta(id);
    }

    private PaymentIntent retrievePaymentIntent(String paymentIntentId) throws StripeException {
        Stripe.apiKey = secretKey; // Reemplaza con tu clave secreta de Stripe

        return PaymentIntent.retrieve(paymentIntentId);
    }
}

