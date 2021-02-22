package com.example.couponsystem.tables;

import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@IdClass(DoublePrimaryKey.class)
public class CustomersVsCoupons
{
    @Id
    private int couponID;
    @Id
    private int customerID;


    public CustomersVsCoupons()
    {

    }

    public int getCouponID()
    {
        return couponID;
    }

    public void setCouponID(int couponID)
    {
        this.couponID = couponID;
    }

    public int getCustomerID()
    {
        return customerID;
    }

    public void setCustomerID(int customerID)
    {
        this.customerID = customerID;
    }
}
