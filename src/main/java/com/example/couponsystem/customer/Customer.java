package com.example.couponsystem.customer;

import com.example.couponsystem.coupon.Coupon;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table
public class Customer
{
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Transient
    private ArrayList<Coupon> coupons;

    public Customer(int id, String firstName, String lastName, String email, String password, ArrayList<Coupon> coupons)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.coupons = coupons;
    }

    public Customer(String firstName, String lastName, String email, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Customer(int id, String firstName, String lastName, String email, String password)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Customer()
    {

    }

    @Override
    public String toString()
    {
        return "Customer{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", password='" + password + '\'' + ", coupons=" + coupons + '}';
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public ArrayList<Coupon> getCoupons()
    {
        return coupons;
    }

    public void setCoupons(ArrayList<Coupon> coupons)
    {
        this.coupons = coupons;
    }
}
