package com.example.couponsystem.loginManager;

import com.example.couponsystem.enums.eClientType;
import com.example.couponsystem.services.AdminService;
import com.example.couponsystem.services.ClientService;
import com.example.couponsystem.services.CompanyService;
import com.example.couponsystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class LoginManager
{
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;

    private LoginManager(){

    }

    public ClientService login(String email, String password, eClientType clientType)
    {
        ClientService clientToReturn = null;
        switch(clientType)
        {
            case Administrator:
                clientToReturn = adminService;
                break;
            case Company:
                clientToReturn = companyService;
                break;
            case Customer:
                clientToReturn = customerService;
                break;
        }

        if(clientToReturn != null)
        {
            if(!clientToReturn.login(email, password))
            {
//                TODO: check it later.
            }
        }

        return clientToReturn;
    }
}
