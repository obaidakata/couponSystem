package com.example.couponsystem.tables;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="companies")
public class Company
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy ="companyID", fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private Set<Coupon> coupons;

    public Company(String name, String email, String password, Set<Coupon>  coupons)
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

    public Set<Coupon>  getCoupons()
    {
        return coupons;
    }

    public void setCoupons(Set<Coupon>  coupons)
    {
        this.coupons = coupons;
    }


    @Override
    public String toString() {
        return "Company id : " + id +
                "\tname= " + name +
                "\temail= " + email  +
                "\tpassword =" + password +
                "\tcoupons = " + coupons +"\n";
    }
}