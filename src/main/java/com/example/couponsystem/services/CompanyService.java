package com.example.couponsystem.services;

import com.example.couponsystem.tables.Company;
import com.example.couponsystem.utiles.Logger;
import com.example.couponsystem.enums.eCategory;
import com.example.couponsystem.tables.Coupon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Transactional
    public void addCoupon(Coupon couponToAdd) throws Exception
    {
        if(couponToAdd != null)
        {
            if(!couponRepository.existsCouponByCompanyIDAndTitle(companyId, couponToAdd.getTitle()))
            {
                couponToAdd.setCompaniesID(companyId);
                couponRepository.saveAndFlush(couponToAdd);
                logger.log("Adding coupon " + couponToAdd.toString());
            }
            else
            {
                throw new Exception( "This Title is already used!");
            }
        }

    }

    @Transactional
    public void updateCoupon(Coupon couponToUpdate) throws Exception
    {
        if(couponToUpdate != null)
        {
            Coupon couponInDB = couponRepository.findCouponById(couponToUpdate.getId());
            if(couponInDB != null)
            {
                int couponInDBCompanyId = couponInDB.getCompaniesID();
                String couponInDBTitle = couponInDB.getTitle();
                //if couponToUpdate changed title then we should check that the company doesn't have another coupon with this title
                if(couponInDBCompanyId == couponToUpdate.getCompaniesID())
                {
                    if(couponToUpdate.getTitle().equals(couponInDBTitle))
                    {
                        couponRepository.saveAndFlush(couponToUpdate);
                        logger.log("updating coupon  " + couponToUpdate.toString());
                    }
                    else if(!couponRepository.existsCouponByCompanyIDAndTitle(couponInDBCompanyId, couponInDBTitle))
                    {

                        couponRepository.saveAndFlush(couponToUpdate);
                        logger.log("updating coupon  " + couponToUpdate.toString());
                    }
                    else
                    {
                        throw new Exception("This title already exists");
                    }
                }
            }
            else
            {
                throw new Exception("Couldn't find the coupon");
            }
        }
    }

    @Transactional
    public void deleteCoupon(int couponId) throws Exception
    {
        if(couponRepository.existsById(couponId))
        {
            customersVsCouponsRepository.deleteCustomersVsCouponsByCouponID(couponId);
            couponRepository.deleteById(couponId);
            logger.log(String.format("Deleting coupon with id %d", couponId));
        }
        else
        {
            throw new Exception("Couldn't find the coupon");
        }
    }

    public ArrayList<Coupon> getCompanyCoupons()
    {
        Company company = companyRepository.findCompanyById(companyId);
        ArrayList<Coupon> companyCoupons = null;
        if(company!= null)
        {
            companyCoupons = new ArrayList<>(company.getCoupons());
        }

        return companyCoupons == null ? new ArrayList<>() : companyCoupons;
    }

    public ArrayList<Coupon> getCompanyCoupons(eCategory category)
    {
        return getCompanyCoupons().stream()
                .filter(coupon -> coupon.getCategoryID() == category)
                .collect(toCollection(ArrayList::new));
    }

    public ArrayList<Coupon> getCompanyCoupons(double maxPrice)
    {
        return getCompanyCoupons().stream()
                .filter(coupon -> coupon.getPrice() <= maxPrice)
                .collect(toCollection(ArrayList::new));
    }

    public Company getCompanyDetails() throws Exception
    {
        Company company = companyRepository.findCompanyById(companyId);
        if(company != null)
        {

            company.setCoupons(new HashSet<>(getCompanyCoupons()));
        }
        else
        {
            throw new Exception("Couldn't get the company details");
        }

        return company;
    }

    public Integer getCompanyId()
    {
        return companyId;
    }
}

