package com.example.couponsystem.utils.customExceptions;

public class ConnectionException extends Exception
{
    public ConnectionException(String errorMessage)
    {
        super("*Error* Couldn't get connection" + errorMessage);
    }

    public ConnectionException()
    {
        super("*Error* Couldn't get connection");
    }
}
