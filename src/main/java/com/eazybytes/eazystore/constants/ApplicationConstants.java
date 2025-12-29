package com.eazybytes.eazystore.constants;

import org.hibernate.AssertionFailure;

public class ApplicationConstants {

     private ApplicationConstants(){
         throw new AssertionError("utility class cannot be instantiated");

     }

    public static final String JWT_SECRET_KEY = "JWT_SECRET";
    public static final String JWT_SECRET_DEFAULT_VALUE = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";


}
