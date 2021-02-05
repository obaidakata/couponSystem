package com.example.couponsystem.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>
{
    boolean existsCompanyByEmailAndPassword(String email, String password);

    void deleteById(int companyID);

    Company findCompanyById(int companyId);

//    boolean existsCompanyByEmail(String email);
//

//    Company findByEmailAndPassword(String email, String password);

}
