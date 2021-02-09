package com.example.couponsystem.services;

import com.example.couponsystem.tables.Company;
import com.example.couponsystem.customExceptions.Logger;
import com.example.couponsystem.enums.eCategory;
import com.example.couponsystem.tables.Coupon;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@Service
public class CompanyService extends ClientService
{
    private int companyId;
    private Logger logger = new Logger();


    public CompanyService(){}

    @Override
    public boolean login(String email, String password)
    {
        Company company = companyRepository.findCompanyByEmailAndPassword(email, password);
        boolean isLoginSuccessful = false;
        if(company != null)
        {
            companyId = company.getId();
            isLoginSuccessful = true;
        }
        else
        {
            logger.log("Login Failed");
        }

        return isLoginSuccessful;
    }

    public void addCoupon(Coupon couponToAdd)
    {
        if(couponToAdd != null)
        {
            int couponCompanyId = couponToAdd.getCompaniesID();
            Company company = companyRepository.findCompanyById(couponCompanyId);
            boolean isCouponExistsInCompany = false;
            if(company != null)
            {
//                Reading coupons from tables without reading the coupons so the company coupons is still empty
                ArrayList<Coupon> companyCoupons = couponRepository.getCouponsByCompanyID(companyId);
                if(companyCoupons != null && !companyCoupons.isEmpty())
                {
                    for(Coupon companyCoupon : companyCoupons)
                    {
                        if(companyCoupon.getTitle().equals(couponToAdd.getTitle()))
                        {
                            isCouponExistsInCompany = true;
                            break;
                        }
                    }
                }
                else
                {
                    company.setCoupons(new ArrayList<Coupon>(List.of(couponToAdd)));
                }
            }

            if(isCouponExistsInCompany)
            {
                logger.log("This Title is already used!");
            }
            else
            {
                couponRepository.saveAndFlush(couponToAdd);
                logger.log("Add Successfully");
            }
        }

    }

    public void updateCoupon(Coupon couponToUpdate)
    {
        Coupon couponInDB = couponRepository.findCouponById(couponToUpdate.getId());

        if(couponInDB != null)
        {
            if(couponInDB.getCompaniesID() == couponToUpdate.getCompaniesID())
            {
                logger.log(couponInDB.getTitle() + " " + couponToUpdate.getTitle());
                if(couponInDB.getTitle().equals(couponToUpdate.getTitle()))
                {
                    couponRepository.save(couponToUpdate);
                    logger.log("Coupon " + couponToUpdate.getId() + " had been updated!");
                }
                else
                {
                    logger.log("Title");
                }
            }
        }
        else
        {
            logger.log("Couldn't find the coupon");
        }
    }

    public void deleteCoupon(int couponId)
    {
        if(couponRepository.existsById(couponId))
        {
            // TODO: 06/02/2021  this.couponsDAO.deleteCouponPurchaseWithId(couponID);
            couponRepository.deleteById(couponId);
            logger.log("Deleting coupon");
        }
        else
        {
            logger.log("Couldn't find the coupon");
        }
    }

    public ArrayList<Coupon> getCompanyCoupons()
    {
        ArrayList<Coupon> companyCoupons = couponRepository.getCouponsByCompanyID(companyId);
        if(companyCoupons != null)
        {
            return companyCoupons;
        }
        else
        {
            logger.log("while trying to get company coupons");
            return new ArrayList<Coupon>();
        }
    }

    public ArrayList<Coupon> getCompanyCoupons(eCategory category)
    {
        ArrayList<Coupon> coupons = couponRepository.getCouponsByCompanyID(companyId);
        if(coupons != null)
        {
            ArrayList<Coupon> filteredCoupons = coupons.stream()
                    .filter(coupon -> coupon.getCategoryID() == category)
                    .collect(toCollection(ArrayList::new));
            return filteredCoupons;
        }
        else
        {
            return new ArrayList<Coupon>();
        }
    }

    public ArrayList<Coupon> getCompanyCoupons(double maxPrice)
    {
        // TODO: 06/02/2021 Deal with code replication
        Company company = companyRepository.findCompanyById(companyId);
        if(company != null)
        {
            ArrayList<Coupon> coupons = company.getCoupons();
            ArrayList<Coupon> filteredCoupons = coupons.stream()
                    .filter(coupon -> coupon.getPrice() <= maxPrice)
                    .collect(toCollection(ArrayList::new));
            return filteredCoupons;
        }
        else
        {
            return new ArrayList<Coupon>();
        }
    }

    public Company getCompanyDetails()
    {
        Company company = companyRepository.findCompanyById(companyId);
        if(company == null)
        {
            logger.log("Couldn't get the company details");
        }

        return company;
    }

//    public Coupon getCouponByTitle(String title, int companyId)
//    {
//        return couponRepository.findCouponByTitle(title);
//    }
}
