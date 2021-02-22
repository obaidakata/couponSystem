package com.example.couponsystem.tables;

import java.io.Serializable;

public class DoublePrimaryKey implements Serializable
{
    private int couponID;
    private int customerID;

    public DoublePrimaryKey(int couponId, int customer)
    {
        this.couponID = couponId;
        this.customerID = customer;
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
