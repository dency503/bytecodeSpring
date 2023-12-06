package com.bytecode.bytecodeecommerce.Service;


import com.bytecode.bytecodeecommerce.dao.PaymentIntentDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.param.ChargeCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class PaymentService {

    @Value("${stripe.key.secret}")
    String secretKey;
    public PaymentIntent paymentIntent(PaymentIntentDTO paymentIntentDto) throws StripeException {
        // Set the Stripe API key
        Stripe.apiKey = secretKey;

        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");
        Map<String, Object> params = new HashMap<>();






        params.put("amount", paymentIntentDto.getAmount());
        params.put("currency", paymentIntentDto.getCurrency());
        params.put("description", paymentIntentDto.getDescription());
        params.put("payment_method_types", paymentMethodTypes);




        return PaymentIntent.create(params);
    }

    public PaymentIntent confirm(String paymentIntentId) {
        try {
            // Set the Stripe API key
          Stripe.apiKey = secretKey;

            // Retrieve an existing PaymentIntent by its ID
            PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);

            // Create parameters for confirmation
            Map<String, Object> confirmationParams = new HashMap<>();
            confirmationParams.put("payment_method", "pm_card_visa");

            // Confirm the PaymentIntent with the specified parameters
            PaymentIntent updatedPaymentIntent = paymentIntent.confirm(confirmationParams);

            // Handle the updated PaymentIntent as needed
            System.out.println("PaymentIntent confirmed: " + updatedPaymentIntent);

            // Return the updated PaymentIntent
            return updatedPaymentIntent;

        } catch (StripeException e) {
            // Handle exceptions
            e.printStackTrace();
            return null; // Return null or throw an exception based on your error handling strategy
        }
    }

    public String procesarPago(String token) throws StripeException {
        Stripe.apiKey = secretKey;

        Charge charge = Charge.create(
                new ChargeCreateParams.Builder()
                        .setAmount(1000L) // Monto en centavos (ejemplo: $10.00)
                        .setCurrency("usd")
                        .setSource(token)
                        .setDescription("Compra en línea")
                        .build()
        );

        // La transacción se ha completado con éxito
        return "Pago exitoso";
    }

    public PaymentIntent cancelPaymentIntent(String paymentIntentId) {
        try {
            // Set the Stripe API key
            Stripe.apiKey = secretKey;

            // Retrieve an existing PaymentIntent by its ID
            PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);

            // Cancel the PaymentIntent
            PaymentIntent canceledPaymentIntent = paymentIntent.cancel();

            // Handle the canceled PaymentIntent as needed
            System.out.println("PaymentIntent canceled: " + canceledPaymentIntent);

            // Return the canceled PaymentIntent
            return canceledPaymentIntent;

        } catch (StripeException e) {
            // Handle exceptions
            e.printStackTrace();
            return null; // Return null or throw an exception based on your error handling strategy
        }
    }
}
