package com.example.couponsystem.tables.tablesRepo;

import com.example.couponsystem.tables.CustomersVsCoupons;
import com.example.couponsystem.tables.DoublePrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface CustomersVsCouponsRepository extends JpaRepository<CustomersVsCoupons, DoublePrimaryKey>
{
        void deleteByCouponID(int couponID);
        boolean existsByCouponID(int couponId);

        @Query("SELECT u.couponID FROM CustomersVsCoupons u WHERE u.customerID = :customerID")
        ArrayList<Integer> getCouponsIdByCustomerID(@Param("customerID")int customerID);
}
