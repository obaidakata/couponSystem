package com.example.couponsystem.tests;

import com.example.couponsystem.utiles.Logger;
import com.example.couponsystem.enums.eCategory;
import com.example.couponsystem.enums.eClientType;
import com.example.couponsystem.loginManager.LoginManager;
import com.example.couponsystem.services.AdminService;
import com.example.couponsystem.services.CompanyService;
import com.example.couponsystem.tables.Company;
import com.example.couponsystem.tables.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Component
@Scope("singleton")
public class CompanyServiceTest
{
    LoginManager loginManager;

    AdminService adminService;

    Map<String, Coupon[]> companiesCoupons;
    private Company[] companies;

    Logger logger = new Logger();

    @Autowired
    public CompanyServiceTest(LoginManager loginManager)

    {
        this.loginManager = loginManager;
        adminService = (AdminService) loginManager.login("admin@admin.com", "admin", eClientType.Administrator);
    }

    private void initCoupons()
    {
        companiesCoupons = new HashMap<>();
        initKFCCoupons();
        initMCDCoupons();
        initVicCoupons();
    }

    private void initKFCCoupons()
    {
        Company kfc = adminService.getCompanyByName("KFC");
        if(kfc == null)
        {
            return;
        }

        int kfcId = kfc.getId();
        companiesCoupons.put(
                "KFC",
                new Coupon[]{
                        new Coupon(
                                kfcId,
                                eCategory.Food,
                                "5 Nagets",
                                "Chicken Naget Description",
                                LocalDate.of(2019, Month.MARCH, 1),
                                LocalDate.of(2022, Month.MARCH, 20),
                                2,
                                60.90,
                                "KFC_5_Nagets.png"),

                        new Coupon(
                                kfcId,
                                eCategory.Food,
                                "4 Nagets",
                                "Chicken Naget Description",
                                LocalDate.of(2019, Month.MARCH,20),
                                LocalDate.of(2022, Month.FEBRUARY,5),
                                2,
                                44.90,
                                "KFC_4_Nagets.png"),

                        new Coupon(
                                kfcId,
                                eCategory.Restaurant,
                                "Family dinner",
                                "Chicken Breast and Naget Description",
                                LocalDate.of(2020, Month.DECEMBER,1),
                                LocalDate.of(2022, Month.FEBRUARY,10),
                                5,
                                80.90,
                                "KFC_4_Family_Dinner.png"),

                        new Coupon(
                                kfcId,
                                eCategory.Electricity,
                                "test for delete",
                                "Will be deleted",
                                LocalDate.of(2020, Month.DECEMBER,1),
                                LocalDate.of(2022, Month.FEBRUARY,8),
                                2,
                                80.90,
                                "KFC_4_Family_Dinner.png")
                });
    }

    private void initVicCoupons()
    {
        Company vic = adminService.getCompanyByName("Vic");
        if(vic == null)
        {
            return;
        }

        int vicId = vic.getId();
        companiesCoupons.put(
                "Vic",
                new Coupon[]{
                        new Coupon(
                                vicId,
                                eCategory.Vacation,
                                "Vic Trip EURO",
                                "description ex1",
                                LocalDate.of(2019, Month.MARCH,1),
                                LocalDate.of(2022, Month.MAY,5),
                                2,
                                4000,
                                "Europe.png"),

                        new Coupon(
                                vicId,
                                eCategory.Vacation,
                                "Vic Trip USA",
                                "description ex2",
                                LocalDate.of(2019, Month.MARCH,1),
                                LocalDate.of(2022, Month.FEBRUARY,24),
                                2,
                                5000,
                                "USA.png"),

                        new Coupon(
                                vicId,
                                eCategory.Restaurant,
                                "Vic 3 Michlean Stars Restaurant",
                                "description ex3",
                                LocalDate.of(2020, Month.DECEMBER,1),
                                LocalDate.of(2022, Month.FEBRUARY,1),
                                2,
                                1499.90,
                                "Restaurant.png")
                });
    }

    private void initMCDCoupons()
    {
        Company mcd = adminService.getCompanyByName("MCD");
        if(mcd == null)
        {
            return;
        }

        int mcdID = mcd.getId();
        companiesCoupons.put(
                "MCD",
                new Coupon[]{
                        new Coupon(
                                mcdID,
                                eCategory.Food,
                                "MC Royal",
                                "description ex1",
                                LocalDate.of(2019, Month.MARCH,1),
                                LocalDate.of(2022, Month.MARCH,1),
                                2,
                                60.90,
                                "MCD_Royal.png"),

                        new Coupon(
                                mcdID,
                                eCategory.Food,
                                "MC Royal Double",
                                "description ex2",
                                LocalDate.of(2019, Month.MARCH,1),
                                LocalDate.of(2021, Month.DECEMBER,27),
                                2,
                                44.90,
                                "MCD_Double_Royal.png"),

                        new Coupon(
                                mcdID,
                                eCategory.Restaurant,
                                "Birthday in MCD",
                                "description ex3",
                                LocalDate.of(2020, Month.DECEMBER,1),
                                LocalDate.of(2022, Month.FEBRUARY,1),
                                2,
                                80.90,
                                "MCD_Family_Birthday.png")
                });
    }

    private void initCompanies()
    {
        companies = new Company[]{
                new Company( "KFC", "company1@gmail.com", "company1"),
                new Company( "Vic",  "company2@gmail.com", "company2"),
                new Company("MCD",  "company3@gmail.com", "company3")
        };
    }

    public void addCoupon()
    {
        initCompanies();
        initCoupons();
        for(Company company : companies)
        {
            CompanyService companyService = (CompanyService) loginManager.login(company.getEmail(),company.getPassword(), eClientType.Company);
            if(companyService != null)
            {
                Coupon[] companyCoupons = companiesCoupons.get(company.getName());
                int x = 0;
                for(Coupon coupon : companyCoupons)
                {
                    LocalDate start = LocalDate.now().withMonth(2);
                    LocalDate end;
                    if(x % 2 == 0)
                    {
                        end = LocalDate.now().withDayOfMonth(3);
                    }
                    else
                    {
                        end = LocalDate.now().withYear(2022);
                    }
                    x++;
                    coupon.setStartDate(start);
                    coupon.setEndDate(end);
                    try
                    {
                        companyService.addCoupon(coupon);
                    }
                    catch(Exception e)
                    {
                        logger.log(e.getMessage());
                    }
                    logger.log(coupon.toString());
                }
            }
        }
    }

    public void updateCoupon()
    {
        initCompanies();
        for(Company company : companies)
        {
            CompanyService companyService = (CompanyService) loginManager.login(company.getEmail(),company.getPassword(), eClientType.Company);
            if(companyService != null)
            {
                try
                {
                    ArrayList<Coupon> companyCoupons = companyService.getCompanyCoupons();
                    if( companyCoupons != null)
                    {
                        for(Coupon coupon : companyCoupons)
                        {
                            coupon.setAmount(5);
                            coupon.setPrice(coupon.getPrice() + 10);
                            companyService.updateCoupon(coupon);
                            logger.log(coupon.toString());
                        }
                    }
                }
                catch(Exception e)
                {
                    logger.log(e.getMessage());
                }
            }
        }
    }

    private CompanyService loginCompany(String email, String password)
    {
        return (CompanyService) loginManager.login(email, password, eClientType.Company);
    }

    public void deleteCoupon()
    {
        initCompanies();
        for(Company company : companies)
        {
            CompanyService companyService = loginCompany(company.getEmail(),company.getPassword());
            if(companyService != null)
            {
                try
                {
                    ArrayList<Coupon> companyCoupons = companyService.getCompanyCoupons();
                    if(companyCoupons != null)
                    {
                        for(Coupon coupon : companyCoupons)
                        {
                            companyService.deleteCoupon(coupon.getId());
                        }
                    }
                }
                catch(Exception e)
                {
                    logger.log(e.getMessage());
                }
            }
        }
    }


    public void getCompanyCoupons()
    {
        initCompanies();
        for(Company company : companies)
        {
            CompanyService companyService = loginCompany(company.getEmail(),company.getPassword());
            if(companyService != null)
            {
                logger.log(companyService.getCompanyCoupons());
            }
        }
    }

    public void testGetCompanyCoupons()
    {
        initCompanies();
        for(Company company : companies)
        {
            CompanyService companyService = loginCompany(company.getEmail(),company.getPassword());
            if(companyService != null)
            {
                logger.log(companyService.getCompanyCoupons(eCategory.Food));
            }
        }
    }

    public void testGetCompanyCoupons1()
    {
        initCompanies();
        for(Company company : companies)
        {
            CompanyService companyService = loginCompany(company.getEmail(),company.getPassword());
            if(companyService != null)
            {
                logger.log(companyService.getCompanyCoupons(100));
            }
        }
    }

    public void getCompanyDetails()
    {
        initCompanies();
        for(Company company :companies)
        {
            CompanyService companyService = loginCompany(company.getEmail(),company.getPassword());
            if(companyService != null)
            {
                try
                {
                    Company companyInDB = companyService.getCompanyDetails();
                    if(companyInDB != null)
                    {
                        logger.log(companyInDB.toString());
                    }
                }
                catch(Exception e)
                {
                    logger.log(e.getMessage());
                }

            }
        }
    }
}