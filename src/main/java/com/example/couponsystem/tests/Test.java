package com.example.couponsystem.tests;

import com.example.couponsystem.loginManager.LoginManager;
import com.example.couponsystem.utiles.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
class Test implements CommandLineRunner
{
    private LoginManager loginManager;

    @Autowired
    private AdminServiceTest adminServiceTest;
    @Autowired
    private CompanyServiceTest companyServiceTest;
    @Autowired
    private CustomerServiceTest customerServiceTest;

    private Logger logger = new Logger();

    @Override
    public void run(String... args) throws Exception
    {
        adminServiceTest.addCompany();
        adminServiceTest.updateCompany();
        adminServiceTest.getAllCompanies();

        adminServiceTest.addCustomer();
        adminServiceTest.updateCustomer();
        adminServiceTest.getAllCustomers();

        companyServiceTest.addCoupon();
        companyServiceTest.getCompanyCoupons();

        customerServiceTest.purchaseCoupon();
        customerServiceTest.getCustomerCoupons();
        customerServiceTest.testGetCustomerCoupons();
        customerServiceTest.testGetCustomerCoupons1();
        customerServiceTest.getCustomerDetails();


        companyServiceTest.updateCoupon();
        companyServiceTest.testGetCompanyCoupons();
        companyServiceTest.testGetCompanyCoupons1();
        companyServiceTest.getCompanyDetails();

//        deleting all data
        companyServiceTest.deleteCoupon();
        adminServiceTest.deleteCompany();
        adminServiceTest.deleteCustomer();
        logger.log("Test finished");
    }
}

