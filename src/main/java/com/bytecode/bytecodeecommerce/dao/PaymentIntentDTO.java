package com.bytecode.bytecodeecommerce.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentIntentDTO {
    public enum Currency {
        usd, eur
    }

    private String description;
    private String email;
    private String name;
    private int amount;
    private Currency currency;
    private String billingAddress;
}