package com.stackfortech.demohateoas.errorHandler;

import com.stackfortech.demohateoas.response.ErrorResponse;
import org.springframework.stereotype.Service;

@Service
public class ErrorHandlerService{

    public ErrorResponse insufficientFunds(String message, int statusCode, Object object)
    {
        ErrorResponse errorResponse = new ErrorResponse(message,object,statusCode);
        return errorResponse;
    }

    public ErrorResponse inValidAccountNumber(String message,int statusCode,Object object)
    {
        ErrorResponse errorResponse = new ErrorResponse(message,object,statusCode);
        return errorResponse;
    }

    public ErrorResponse accountNotActive(String message,int statusCode,Object object)
    {
        ErrorResponse errorResponse = new ErrorResponse(message,object,statusCode);
        return errorResponse;
    }

    public ErrorResponse invalidToAccount(String message,int statusCode,Object object)
    {
        ErrorResponse errorResponse = new ErrorResponse(message,object,statusCode);
        return errorResponse;
    }
    public ErrorResponse invalidFromAccount(String message,int statusCode,Object object)
    {
        ErrorResponse errorResponse = new ErrorResponse(message,object,statusCode);
        return errorResponse;
    }



}
