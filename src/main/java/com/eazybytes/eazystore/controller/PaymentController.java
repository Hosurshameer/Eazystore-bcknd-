package com.eazybytes.eazystore.controller;


import com.eazybytes.eazystore.dto.PaymentRequestDto;
import com.eazybytes.eazystore.dto.PaymentResponseDto;
import com.eazybytes.eazystore.service.iPaymentService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final iPaymentService ipaymentService;


    public ResponseEntity<PaymentResponseDto> createPaymentIntent(@RequestBody PaymentRequestDto paymentRequestDto){

        PaymentResponseDto response= ipaymentService.createPaymentIntent(paymentRequestDto);



        return ResponseEntity.ok(response);
    }

}
