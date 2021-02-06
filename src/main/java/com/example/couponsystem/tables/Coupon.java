package com.example.couponsystem.tables;

import com.example.couponsystem.enums.eCategory;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="coupons")
public class Coupon
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "amount")
    private int amount;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "image")
    private String image;

    @Column(name = "companies_id")
    private int companiesId;

    @Column(name = "categories_id")
    private eCategory categoryId;




    public Coupon(int companiesID, eCategory categoryId, String title, String description, LocalDate startDate, LocalDate endDate, int amount, double price, String image)
    {
        this.companiesId = companiesID;
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    public Coupon(int companiesID)
    {
        this.companiesId = companiesID;
    }

    public Coupon()
    {

    }


    @Override
    public String toString()
    {
        return "Coupon{" + "id=" + id + ", companyID=" + companiesId + ", category=" + categoryId + ", title='" + title + '\'' + ", description='" + description + '\'' + ", startDate=" + startDate + ", endDate=" + endDate + ", amount=" + amount + ", price=" + price + ", image='" + image + '\'' + '}';
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getCompaniesID()
    {
        return companiesId;
    }

    public void setCompaniesID(int companyID)
    {
        this.companiesId = companyID;
    }

    public eCategory getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(eCategory category)
    {
        this.categoryId = category;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public void setEndDate(LocalDate endDate)
    {
        this.endDate = endDate;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public void purchase()
    {
        if(amount > 0)
        {
            amount--;
        }
    }
}
