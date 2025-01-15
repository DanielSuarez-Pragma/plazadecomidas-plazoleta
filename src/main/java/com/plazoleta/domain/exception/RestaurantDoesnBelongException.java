package com.plazoleta.domain.exception;

public class RestaurantDoesnBelongException extends  IllegalArgumentException{
    public RestaurantDoesnBelongException() {
        super("This restaurant does not belong to the specified user.");
    }
}
