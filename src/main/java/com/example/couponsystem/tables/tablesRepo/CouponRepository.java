package com.example.couponsystem.tables.tablesRepo;

import com.example.couponsystem.tables.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer>
{
    Coupon findCouponById(int couponId);

    // TODO: 06/02/2021 implement
    ArrayList<Coupon> getCouponsById(int id);
    ArrayList<Coupon> getCouponsByCompanyID(int companyId);

    @Query("SELECT u FROM Coupon u WHERE u.endDate < :today")
    ArrayList<Coupon> findCouponByEndDateBefore(@Param("today") LocalDate today);

    @Modifying
    @Query("DELETE FROM Coupon WHERE endDate < :today")
    void deleteCouponByEndDateBefore(@Param("today") LocalDate today);

    boolean existsCouponByCompanyIDAndTitle(int companyId, String title);

}
