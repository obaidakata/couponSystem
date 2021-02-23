package com.example.couponsystem.tables;

import java.io.Serializable;
import java.util.Objects;

public class DoublePrimaryKey implements Serializable
{
    private int couponID;
    private int customerID;

    public DoublePrimaryKey(int couponId, int customer)
    {
        this.couponID = couponId;
        this.customerID = customer;
    }

    public DoublePrimaryKey()
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

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        DoublePrimaryKey that = (DoublePrimaryKey) o;
        return couponID == that.couponID && customerID == that.customerID;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(couponID, customerID);
    }
}
