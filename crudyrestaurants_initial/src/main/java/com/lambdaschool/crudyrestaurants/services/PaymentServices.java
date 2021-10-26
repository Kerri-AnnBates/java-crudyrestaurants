package com.lambdaschool.crudyrestaurants.services;

import com.lambdaschool.crudyrestaurants.models.Payment;

public interface PaymentServices {
    // Allow other classes to save a payment.
    Payment save(Payment payment);
}
