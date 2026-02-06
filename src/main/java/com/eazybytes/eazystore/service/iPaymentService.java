package com.eazybytes.eazystore.service;

import com.eazybytes.eazystore.dto.PaymentRequestDto;
import com.eazybytes.eazystore.dto.PaymentResponseDto;
import org.springframework.context.annotation.Configuration;


public interface iPaymentService {

    PaymentResponseDto createPaymentIntent(PaymentRequestDto paymentRequestDto);
}
