package com.eazybytes.eazystore.constants;

import org.hibernate.AssertionFailure;

public class ApplicationConstants {

     private ApplicationConstants(){
         throw new AssertionError("utility class cannot be instantiated");

     }

    public static final String JWT_SECRET_KEY = "JWT_SECRET";
    public static final String JWT_SECRET_DEFAULT_VALUE = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
    public static  final String JWT_HEADER="Authorization";


    public static  final String ORDER_STATUS_CREATED="CREATED";
    public static  final String ORDER_STATUS_PAID="Paid";
    public static  final String ORDER_STATUS_SHIPPED="Shipped";
    public static  final String ORDER_STATUS_DELIVERED="Delivered";
    public static  final String ORDER_STATUS_CANCELLED="CANCELLED";
    public  static  final String ORDER_STATUS_CONFIRMED="CONFIRMED";




}
