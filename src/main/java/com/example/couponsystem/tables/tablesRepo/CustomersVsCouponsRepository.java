package com.example.couponsystem.tables.tablesRepo;

import com.example.couponsystem.tables.CustomersVsCoupons;
import com.example.couponsystem.tables.DoublePrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersVsCouponsRepository extends JpaRepository<CustomersVsCoupons, DoublePrimaryKey>
{
//        void deleteByCouponIdAndCustomerId(int couponID, int customerID);
//        void findAllByCoupon_IdAndCustomer_Id(int couponID, int customerID);
        void deleteByCouponID(int couponID);
        boolean existsByCouponID(int couponId);
}
