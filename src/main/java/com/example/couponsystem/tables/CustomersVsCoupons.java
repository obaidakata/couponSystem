package com.example.couponsystem.tables;

import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@IdClass(DoublePrimaryKey.class)
@Table(name = "customers_vs_coupons")
public class CustomersVsCoupons
{
    @Id
    @Column(name = "COUPON_ID")
    private int couponID;

    @Id
    @Column(name = "CUSTOMERS_ID")
    private int customerID;


    public CustomersVsCoupons()
    {

    }

    public CustomersVsCoupons(int couponID, int customerID)
    {
        this.couponID = couponID;
        this.customerID = customerID;
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
