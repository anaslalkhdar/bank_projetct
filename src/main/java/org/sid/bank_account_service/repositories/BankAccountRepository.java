package org.sid.bank_account_service.repositories;

import org.sid.bank_account_service.entities.Bankaccount;
import org.sid.bank_account_service.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "bankAccounts", collectionResourceRel = "bankAccounts")
public interface BankAccountRepository extends JpaRepository<Bankaccount, String> {
    @RestResource(path = "byType")
    List<Bankaccount> findByType(@Param("t") AccountType type);
}
