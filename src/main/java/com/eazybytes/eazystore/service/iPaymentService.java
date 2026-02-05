package com.eazybytes.eazystore.service;

import com.eazybytes.eazystore.dto.PaymentRequestDto;
import com.eazybytes.eazystore.dto.PaymentResponseDto;

public interface iPaymentService {

    PaymentResponseDto createPaymentIntent(PaymentRequestDto paymentRequestDto);
}
