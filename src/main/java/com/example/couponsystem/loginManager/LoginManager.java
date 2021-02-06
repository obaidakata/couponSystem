package com.example.couponsystem.loginManager;

import com.example.couponsystem.enums.eClientType;
import com.example.couponsystem.services.AdminService;
import com.example.couponsystem.services.ClientService;
import com.example.couponsystem.services.CompanyService;
import com.example.couponsystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

public class LoginManager
{
    private static LoginManager instance = null;

    private AdminService  adminService;
    private CompanyService companyService;
    private CustomerService customerService;

    public void setAdminService(AdminService adminService)
    {
        this.adminService = adminService;
    }

    public void setCompanyService(CompanyService companyService)
    {
        this.companyService = companyService;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }


    private LoginManager()
    {
    }

    public static LoginManager getInstance()
    {
        if(instance == null)
        {
            instance = new LoginManager();
        }

        return instance;
    }

    @PostConstruct
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

        if(clientToReturn.login(email, password))
        {
            return clientToReturn;
        }

        return null;
    }
}
