package com.example.couponsystem.loginManager;

import com.example.couponsystem.enums.eClientType;
import com.example.couponsystem.services.AdminService;
import com.example.couponsystem.services.ClientService;
import com.example.couponsystem.services.CompanyService;
import com.example.couponsystem.services.CustomerService;
import com.example.couponsystem.utiles.Singelton;

public class LoginManager
{
    private static LoginManager instance = null;

    private LoginManager()
    {
    }

    public static LoginManager getInstance()
    {
        if(instance != null)
        {
            instance = new LoginManager();
        }

        return instance;
    }

    public ClientService login(String email, String password, eClientType clientType)
    {
        ClientService clientToReturn = null;
        switch(clientType)
        {
            case Administrator:
                clientToReturn = new AdminService();
                break;
            case Company:
                clientToReturn = new CompanyService();
                break;
            case Customer:
                clientToReturn = new CustomerService();
                break;
        }

        if(clientToReturn.login(email, password))
        {
            return clientToReturn;
        }

        return null;
    }
}
