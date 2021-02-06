package com.example.couponsystem.controllers;

import com.example.couponsystem.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/company")
public class CompanyController
{
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService)
    {
        this.companyService = companyService;
    }

//    @RequestMapping(path="{email}/{password}")
//    public String isCompanyExists(
//            @PathVariable("email") String email,
//            @PathVariable("password") String password)
//    {
//
//        return companyService.isCompanyExistsByEmailAndPassword(email, password)?"True":"False";
//    }
//
//    @PostMapping
//    public void addCompany(@RequestBody Company company)
//    {
//        companyService.addCompany(company);
//    }
//
//
//    @PutMapping
//    public void updateCompany(@RequestBody Company company){
//        companyService.updateCompany(company);
//    }
//
//
//    @DeleteMapping(path = "{companyId}")
//    public void deleteCompany(@PathVariable("companyId") int companyId)
//    {
//        companyService.deleteCompany(companyId);
//    }
//
//    @GetMapping
//    public ArrayList<Company> getAllCompanies()
//    {
//        return companyService.getAllCompanies();
//    }
//
//    @GetMapping(path="{companyId}")
//    public Company getOneCompany(@PathVariable int companyId)
//    {
//        return companyService.getOneCompany(companyId);
//    }
}
