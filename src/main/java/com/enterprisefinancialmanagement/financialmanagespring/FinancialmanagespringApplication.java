package com.enterprisefinancialmanagement.financialmanagespring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.enterprisefinancialmanagement.financialmanagespring.dao")
public class FinancialmanagespringApplication {

    public static void main(String[] args) {

        SpringApplication.run(FinancialmanagespringApplication.class, args);
    }

}
