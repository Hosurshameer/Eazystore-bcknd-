package com.eazybytes.eazystore.service.impl;

import com.eazybytes.eazystore.dto.PaymentRequestDto;
import com.eazybytes.eazystore.dto.PaymentResponseDto;
import com.eazybytes.eazystore.service.iPaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;


@Service
public class PaymentServiceImpl implements iPaymentService {
    @Override
    public PaymentResponseDto createPaymentIntent(PaymentRequestDto paymentRequestDto) {
        try {
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(paymentRequestDto.amount())
                    .setCurrency(paymentRequestDto.currency())
                    .addPaymentMethodType("card").build();
            PaymentIntent paymentIntent=PaymentIntent.create(params);
            return new PaymentResponseDto(paymentIntent.getClientSecret());
        }catch(StripeException e){
            throw new RuntimeException("Faied to create payment intent");

        }
    }
}
