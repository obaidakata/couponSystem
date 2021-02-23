package com.example.couponsystem.services;

import com.example.couponsystem.tables.Customer;
import com.example.couponsystem.customExceptions.Logger;
import com.example.couponsystem.enums.eCategory;
import com.example.couponsystem.tables.Coupon;
import com.example.couponsystem.tables.CustomersVsCoupons;
import com.example.couponsystem.tables.DoublePrimaryKey;
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
                    couponRepository.saveAndFlush(coupon);
                    customersVsCouponsRepository.saveAndFlush(new CustomersVsCoupons(couponId, customerId));
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
        ArrayList<Coupon> customerCoupons = new ArrayList<>();
        if(customer != null)
        {
            ArrayList<Integer> customerCouponsID = customersVsCouponsRepository.getCouponsIdByCustomerID(customerId);
            if(!customerCouponsID.isEmpty())
            {
                for(int couponId : customerCouponsID)
                {
                    Coupon coupon = couponRepository.findCouponById(couponId);
                    if(coupon != null)
                    {
                        customerCoupons.add(coupon);
                    }
                }
            }
        }
        else
        {
            logger.log("while getting customers coupon");
        }

        return customerCoupons;
    }

    public ArrayList<Coupon> getCustomerCoupons(eCategory category)
    {
        Customer customer = customerRepository.findCustomerById(customerId);
        if(customer != null)
        {
            return getCustomerCoupons().stream()
                            .filter(coupon -> coupon.getCategoryID() == category)
                            .collect(toCollection(ArrayList::new));
        }
        else
        {
            return new ArrayList<>();
        }
    }

    public ArrayList<Coupon> getCustomerCoupons(double maxPrice)
    {
        Customer customer = customerRepository.findCustomerById(customerId);
        if(customer != null)
        {
            return getCustomerCoupons().stream()
                    .filter(coupon -> coupon.getPrice() <= maxPrice)
                    .collect(toCollection(ArrayList::new));
        }
        else
        {
            return new ArrayList<Coupon>();
        }
    }

    public Customer getCustomerDetails()
    {
        Customer customer = customerRepository.findCustomerById(customerId);
        if(customer != null)
        {
            customer.setCoupons(getCustomerCoupons());
        }
        else
        {
            logger.log("Couldn't get the customer details");
        }

        return customer;
    }

    public Integer getCustomerId(String email, String password)
    {
        if(login(email, password))
        {
            return customerId;
        }
        else
        {
            //TODO : Fix this shit
            return null;
        }
    }
}
