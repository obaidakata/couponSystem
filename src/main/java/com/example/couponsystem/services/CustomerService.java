package com.example.couponsystem.services;

import com.example.couponsystem.tables.Customer;
import com.example.couponsystem.customExceptions.Logger;
import com.example.couponsystem.enums.eCategory;
import com.example.couponsystem.tables.Coupon;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.util.stream.Collectors.toCollection;

@Service
public class CustomerService extends ClientService
{
    private int customerId;
    private Logger logger = new Logger();

    public CustomerService()
    {
    }

    public boolean login(String email, String password)
    {
        Customer customer = customerRepository.findCustomerByEmailAndPassword(email, password);
        boolean isLoginSuccessful = false;
        if(customer != null)
        {
            customerId = customer.getId();
            isLoginSuccessful = true;
        }
        else
        {
            logger.log("Login Failed");
        }

        return isLoginSuccessful;
    }

    public void purchaseCoupon(int couponId)
    {
        Coupon coupon = couponRepository.findCouponById(couponId);
        if(coupon != null)
        {
            if(coupon.getAmount() > 0)
            {
                if(LocalDate.now().isBefore(coupon.getEndDate()))
                {
                    coupon.purchase();
                    couponRepository.save(coupon);
//                    couponRepository.addCouponPurchase(customerId, couponId);
                    logger.log("You bought " + couponId);
                }
                else
                {
                    logger.log("Coupon " + coupon.getId() + " expired");
                }
            }
            else
            {
                logger.log("Coupon " + coupon.getId() + " sold out");
            }

        }
        else
        {
            logger.log("Couldn't find the coupon");
        }
    }

    public ArrayList<Coupon> getCustomerCoupons()
    {
        Customer customer = customerRepository.findCustomerById(customerId);
        if(customer != null)
        {
            return customer.getCoupons();
        }
        else
        {
            logger.log("while getting customers coupon");
            return new ArrayList<>();
        }
    }

    public ArrayList<Coupon> getCustomerCoupons(eCategory category)
    {
        Customer customer = customerRepository.findCustomerById(customerId);
        if(customer != null)
        {
            ArrayList<Coupon> coupons = customer.getCoupons();
            ArrayList<Coupon> filteredCoupons = coupons.stream()
                    .filter(coupon -> coupon.getCategoryID() == category)
                    .collect(toCollection(ArrayList::new));
            return filteredCoupons;
        }
        else
        {
            return new ArrayList<>();
        }
    }

    public ArrayList<Coupon> getCustomerCoupons(double maxPrice)
    {
        // TODO: 06/02/2021 Deal with code replication
        Customer customer = customerRepository.findCustomerById(customerId);
        if(customer != null)
        {
            ArrayList<Coupon> coupons = customer.getCoupons();
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

    public Customer getCustomerDetails()
    {
        Customer customer = customerRepository.findCustomerById(customerId);
        if(customer == null)
        {
            logger.log("Couldn't get the customer details");
        }

        return customer;
    }

}
