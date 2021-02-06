package com.example.couponsystem.tables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer>
{
    Coupon findCouponById(int couponId);

    // TODO: 06/02/2021 implement

//    void addCouponPurchase(int customerId, int couponId);

//    @Modifying
//    @Query("DELETE FROM CUSTOMERS_VS_COUPONS  c WHERE c.COUPON_ID=:couponId")
//    void deletePurchaseCouponByCouponID(@Param("couponId") int couponId);


}
