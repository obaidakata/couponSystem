package com.example.couponsystem.services;

import com.example.couponsystem.tables.Company;
import com.example.couponsystem.customExceptions.Logger;
import com.example.couponsystem.enums.eCategory;
import com.example.couponsystem.tables.Coupon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.Transient;
import java.time.LocalDate;
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
    public void addCoupon(Coupon couponToAdd)
    {
        if(couponToAdd != null)
        {
            couponToAdd.setCompaniesID(companyId);
            Company company = companyRepository.findCompanyById(companyId);
            boolean isCouponExistsInCompany = false;
            if(company != null)
            {
                Set<Coupon> companyCoupons = company.getCoupons();
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
                    //TODO : is not necessary becouse we will not do saveAnd Flush
                    company.setCoupons(new HashSet<Coupon>(List.of(couponToAdd)));
                }
            }

            if(isCouponExistsInCompany)
            {
                logger.log("This Title is already used!");
            }
            else
            {
                couponToAdd.setCompaniesID(companyId);
                couponRepository.saveAndFlush(couponToAdd);
                logger.log("Adding coupon " + couponToAdd.toString());
            }
        }

    }

    @Transactional
    public void updateCoupon(Coupon couponToUpdate)
    {
        if(couponToUpdate != null)
        {
            Coupon couponInDB = couponRepository.findCouponById(couponToUpdate.getId());

            if(couponInDB != null)
            {
                if(couponInDB.getCompaniesID() == couponToUpdate.getCompaniesID())
                {
                    logger.log(couponInDB.getTitle() + " " + couponToUpdate.getTitle());
                    if(couponInDB.getTitle().equals(couponToUpdate.getTitle()))
                    {
                        couponRepository.saveAndFlush(couponToUpdate);
                        logger.log("updating coupon  " + couponToUpdate.toString());
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
    }

    @Transactional
    public void deleteCoupon(int couponId)
    {
        if(couponRepository.existsById(couponId))
        {
            customersVsCouponsRepository.deleteCustomersVsCouponsByCouponID(couponId);
            couponRepository.deleteById(couponId);
            logger.log(String.format("Deleting coupon with id %d", couponId));
        }
        else
        {
            logger.log("Couldn't find the coupon");
        }
    }

    public ArrayList<Coupon> getCompanyCoupons()
    {
        Company company = companyRepository.findCompanyById(companyId);
        if(company != null && company.getCoupons() != null)
        {
            return new ArrayList(company.getCoupons());
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
            return coupons.stream()
                    .filter(coupon -> coupon.getCategoryID() == category)
                    .collect(toCollection(ArrayList::new));
        }
        else
        {
            return new ArrayList<Coupon>();
        }
    }

    public ArrayList<Coupon> getCompanyCoupons(double maxPrice)
    {
        ArrayList<Coupon> coupons = couponRepository.getCouponsByCompanyID(companyId);
        if(coupons != null)
        {
            return coupons.stream()
                    .filter(coupon -> coupon.getPrice() <= maxPrice)
                    .collect(toCollection(ArrayList::new));
        }
        else
        {
            return new ArrayList<Coupon>();
        }
    }

    public Company getCompanyDetails()
    {
        Company company = companyRepository.findCompanyById(companyId);
        if(company != null)
        {
            company.setCoupons(new HashSet<>(getCompanyCoupons()));
        }
        else
        {
            logger.log("Couldn't get the company details");
        }

        return company;
    }

    public Integer getCompanyId()
    {
        return companyId;
    }
}

