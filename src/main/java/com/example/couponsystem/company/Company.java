package com.example.couponsystem.company;

import com.example.couponsystem.coupon.Coupon;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;


@Entity
@Table(name="COMPANIES")
public class Company
{
    @Id
    @SequenceGenerator(
            name = "company_sequence",
            sequenceName = "company_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "company_sequence"
    )

    private int id;
    private String name;
    private String email;
    private String password;

    @Transient
    private ArrayList<Coupon> coupons;

    public Company(String name, String email, String password, ArrayList<Coupon>  coupons)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.coupons = coupons;
    }

    public Company(int id, String name, String email, String password)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Company(int id)
    {
        this.id = id;
    }

    public Company(String name, String email, String password)
    {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Company()
    {

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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

    public ArrayList<Coupon>  getCoupons()
    {
        return coupons;
    }

    public void setCoupons(ArrayList<Coupon>  coupons)
    {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return "\nCompany id : " + id +
                "\n\tname= " + name +
                "\n\temail= " + email  +
                "\n\tpassword =" + password +
                "\n\tcoupons = " + coupons +"\n";
    }
}