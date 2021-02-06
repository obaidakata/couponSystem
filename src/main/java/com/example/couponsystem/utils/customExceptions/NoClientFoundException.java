package com.example.couponsystem.utils.customExceptions;

public class NoClientFoundException extends Exception
{
    public NoClientFoundException(String errorMessage)
    {
        super("*Error* Couldn't find the client:" + errorMessage);
    }

    public NoClientFoundException()
    {
        super("*Error* Couldn't find the client");
    }
}
