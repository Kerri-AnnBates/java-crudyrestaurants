package com.lambdaschool.crudyrestaurants.services;

import com.lambdaschool.crudyrestaurants.models.Payment;
import com.lambdaschool.crudyrestaurants.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PaymentServicesImpl implements PaymentServices {

    @Autowired
    PaymentRepository paymentRepos;

    @Transactional
    @Override
    public Payment save(Payment payment) {
        return paymentRepos.save(payment);
    }
}
