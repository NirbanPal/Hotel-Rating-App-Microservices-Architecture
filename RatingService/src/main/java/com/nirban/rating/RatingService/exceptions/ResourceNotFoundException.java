package com.nirban.rating.RatingService.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super("Resource does not found!!");
    }

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
