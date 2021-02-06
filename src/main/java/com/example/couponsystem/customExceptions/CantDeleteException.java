package com.example.couponsystem.customExceptions;

public class CantDeleteException extends Exception
{
    public CantDeleteException(String errorMessage)
    {
        super("*Error* Couldn't delete due to " + errorMessage);
    }
}
