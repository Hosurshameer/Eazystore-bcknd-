package com.eazybytes.eazystore.config;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {


     @Value("${stripe.apiKey}")
    private  String apiKey;

     public void init(){
         Stripe.apiKey=apiKey;
     }

}
