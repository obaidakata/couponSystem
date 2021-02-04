package com.example.couponsystem.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>
{
//    boolean isCompanyExists(String i_email, String password);
//
//    boolean isCompanyExistsValidate(String email);
//
//    int getCompanyIdByCredentials (String email, String password);
//
//    void addCompany(Company company) throws ConnectionException, SQLException;
//
//    void updateCompany(Company company) throws ConnectionException, SQLException;

    void deleteById(int companyID);

    Company findCompanyById(int companyId);

    Optional<Company> findCompanyByName(String i_name);
}
