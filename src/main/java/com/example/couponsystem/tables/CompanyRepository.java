package com.example.couponsystem.tables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>
{
    Company findCompanyByEmailAndPassword(String email, String password);
    Company findCompanyById(int companyId);
    Company findCompanyByName(String companyName);
    boolean existsCompanyById(int companyId);
    boolean existsCompanyByName(String name);
    boolean existsCompanyByEmail(String email);
    boolean existsCompanyByIdAndName(int companyId, String companyName);
}
