package com.example.couponsystem.tables.tablesRepo;

import com.example.couponsystem.tables.CustomersVsCoupons;
import com.example.couponsystem.tables.DoublePrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface CustomersVsCouponsRepository extends JpaRepository<CustomersVsCoupons, DoublePrimaryKey>
{
        @Modifying
        @Query("DELETE FROM CustomersVsCoupons u WHERE u.couponID = :couponID")
        void deleteCustomersVsCouponsByCouponID(@Param("couponID") int couponID);

        @Query("SELECT u.couponID FROM CustomersVsCoupons u WHERE u.customerID = :customerID")
        ArrayList<Integer> getCouponsIdByCustomerID(@Param("customerID") int customerID);


        @Modifying
        @Query("DELETE FROM CustomersVsCoupons u WHERE u.customerID = :customerID")
        void deleteCouponsByCustomerID(@Param("customerID") int customerID);

        boolean existsCustomersVsCouponsByCouponIDAndCustomerID(int couponId, int customerId);

}
